package com.wonjoonshin.androidguide

import android.app.Activity
import android.app.AlertDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.nio.charset.StandardCharsets
import java.util.*

class MainActivity : Activity() {
    var mPariedDeviceCount = 0
    lateinit var mDevices: Set<BluetoothDevice>

    // 폰의 블루투스 모듈을 사용하기 위한 오브젝트.
    lateinit var mBluetoothAdapter: BluetoothAdapter
    var mRemoteDevie: BluetoothDevice? = null

    // 스마트폰과 페어링 된 디바이스간 통신 채널에 대응 하는 BluetoothSocket
    lateinit var mSocket: BluetoothSocket
    lateinit var mOutputStream: OutputStream
    lateinit var mInputStream: InputStream
    var mCharDelimiter = '\n'
    lateinit var mWorkerThread: Thread
    lateinit var readBuffer: ByteArray
    var readBufferPosition = 0
    lateinit var mEditReceive: TextView
    lateinit var measured_body_tempreture: LinearLayout
    lateinit var endButton: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mEditReceive = findViewById<View>(R.id.text) as TextView
        measured_body_tempreture = findViewById(R.id.measured_body_tempreture)
        endButton = findViewById(R.id.endButton)

        // 블루투스 활성화 시키는 메소드
        checkBluetooth()
        measured_body_tempreture.setOnClickListener(View.OnClickListener { v: View? ->
            sendData()
            beginListenForData()
            mWorkerThread!!.start()
        })

        endButton.setOnClickListener(View.OnClickListener { v: View? ->
            val s = mEditReceive!!.text.toString() //아스키 코드로 숫자 도착
            var temp = ""
            var a = 0.0
            var b: Int
            for (i in 0 until s.length - 1) {
                b = s[i].toInt() - 48
                if (b == -2) {
                    temp += "."
                } else {
                    temp += b
                }
            }
            try {
                a = temp.toDouble()
            } catch (e: Exception) {
                Toast.makeText(this, "온도값 변환중 오류 발생", Toast.LENGTH_LONG).show()
                finish()
            }

            finish()
        })
    }

    // 블루투스 장치의 이름이 주어졌을때 해당 블루투스 장치 객체를 페어링 된 장치 목록에서 찾아내는 코드.
    fun getDeviceFromBondedList(name: String): BluetoothDevice? {
        // BluetoothDevice : 페어링 된 기기 목록을 얻어옴.
        var selectedDevice: BluetoothDevice? = null
        // getBondedDevices 함수가 반환하는 페어링 된 기기 목록은 Set 형식이며,
        // Set 형식에서는 n 번째 원소를 얻어오는 방법이 없으므로 주어진 이름과 비교해서 찾는다.
        for (deivce in mDevices!!) {
            // getName() : 단말기의 Bluetooth Adapter 이름을 반환
            if (name == deivce.name) {
                selectedDevice = deivce
                break
            }
        }
        return selectedDevice
    }

    fun sendData() {
        try {
            mOutputStream!!.write(1) // 문자열 전송.
        } catch (e: Exception) {  // 문자열 전송 도중 오류가 발생한 경우
            Toast.makeText(applicationContext, "데이터 전송중 오류가 발생", Toast.LENGTH_LONG).show()
            finish() // App 종료
        }
    }

    //  connectToSelectedDevice() : 원격 장치와 연결하는 과정을 나타냄.
    //        실제 데이터 송수신을 위해서는 소켓으로부터 입출력 스트림을 얻고 입출력 스트림을 이용하여 이루어 진다.
    fun connectToSelectedDevice(selectedDeviceName: String) {
        // BluetoothDevice 원격 블루투스 기기를 나타냄.
        mRemoteDevie = getDeviceFromBondedList(selectedDeviceName)
        // java.util.UUID.fromString : 자바에서 중복되지 않는 Unique 키 생성.
        val uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
        try {
            // 소켓 생성, RFCOMM 채널을 통한 연결.
            // createRfcommSocketToServiceRecord(uuid) : 이 함수를 사용하여 원격 블루투스 장치와 통신할 수 있는 소켓을 생성함.
            // 이 메소드가 성공하면 스마트폰과 페어링 된 디바이스간 통신 채널에 대응하는 BluetoothSocket 오브젝트를 리턴함.
            mSocket = mRemoteDevie!!.createRfcommSocketToServiceRecord(uuid)
            mSocket.connect() // 소켓이 생성 되면 connect() 함수를 호출함으로써 두기기의 연결은 완료된다.

            // 데이터 송수신을 위한 스트림 얻기.
            // BluetoothSocket 오브젝트는 두개의 Stream을 제공한다.
            // 1. 데이터를 보내기 위한 OutputStrem
            // 2. 데이터를 받기 위한 InputStream
            mOutputStream = mSocket.getOutputStream()
            mInputStream = mSocket.getInputStream()

            // 데이터 수신 준비.
            beginListenForData()
        } catch (e: Exception) { // 블루투스 연결 중 오류 발생
            Toast.makeText(applicationContext, "블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
            finish() // App 종료
        }
    }

    // 데이터 수신(쓰레드 사용 수신된 메시지를 계속 검사함)
    fun beginListenForData() {
        val handler = Handler()
        readBufferPosition = 0 // 버퍼 내 수신 문자 저장 위치.
        readBuffer = ByteArray(1024) // 수신 버퍼.

        // 문자열 수신 쓰레드.
        mWorkerThread = Thread(object : Runnable {
            var cnt = 0
            override fun run() {
                // interrupt() 메소드를 이용 스레드를 종료시키는 예제이다.
                // interrupt() 메소드는 하던 일을 멈추는 메소드이다.
                // isInterrupted() 메소드를 사용하여 멈추었을 경우 반복문을 나가서 스레드가 종료하게 된다.
                while (cnt < 1) {
                    try {
                        // InputStream.available() : 다른 스레드에서 blocking 하기 전까지 읽은 수 있는 문자열 개수를 반환함.
                        val byteAvailable = mInputStream!!.available() // 수신 데이터 확인
                        if (byteAvailable > 0) {                        // 데이터가 수신된 경우.
                            val packetBytes = ByteArray(byteAvailable)
                            // read(buf[]) : 입력스트림에서 buf[] 크기만큼 읽어서 저장 없을 경우에 -1 리턴.
                            mInputStream!!.read(packetBytes)
                            for (i in 0 until byteAvailable) {
                                val b = packetBytes[i]
                                if (b == mCharDelimiter.toByte()) {
                                    val encodedBytes = ByteArray(readBufferPosition)
                                    //  System.arraycopy(복사할 배열, 복사시작점, 복사된 배열, 붙이기 시작점, 복사할 개수)
                                    //  readBuffer 배열을 처음 부터 끝까지 encodedBytes 배열로 복사.
                                    System.arraycopy(
                                        readBuffer,
                                        0,
                                        encodedBytes,
                                        0,
                                        encodedBytes.size
                                    )
                                    val data = String(encodedBytes, StandardCharsets.US_ASCII)
                                    readBufferPosition = 0
                                    handler.post { // mStrDelimiter = '\n';
                                        mEditReceive!!.text = data
                                    }
                                } else {
                                    readBuffer[readBufferPosition++] = b
                                }
                            }
                        }
                        cnt++
                    } catch (e: Exception) {    // 데이터 수신 중 오류 발생.
                        Toast.makeText(
                            applicationContext,
                            "데이터 수신 중 오류가 발생 했습니다.",
                            Toast.LENGTH_LONG
                        ).show()
                        finish() // App 종료.
                    }
                }
            }
        })
    }

    // 블루투스 지원하며 활성 상태인 경우.
    fun selectDevice() {
        // 블루투스 디바이스는 연결해서 사용하기 전에 먼저 페어링 되어야만 한다
        // getBondedDevices() : 페어링된 장치 목록 얻어오는 함수.
        mDevices = mBluetoothAdapter!!.bondedDevices
        mPariedDeviceCount = mDevices.size
        if (mPariedDeviceCount == 0) { // 페어링된 장치가 없는 경우.
            Toast.makeText(applicationContext, "페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show()
            finish() // App 종료.
        }
        // 페어링된 장치가 있는 경우.
        val builder = AlertDialog.Builder(this)
        builder.setTitle("블루투스 장치 선택")

        // 각 디바이스는 이름과(서로 다른) 주소를 가진다. 페어링 된 디바이스들을 표시한다.
        val listItems: MutableList<String> = ArrayList()
        for (device in mDevices) {
            // device.getName() : 단말기의 Bluetooth Adapter 이름을 반환.
            listItems.add(device.name)
        }
        listItems.add("취소") // 취소 항목 추가.


        // CharSequence : 변경 가능한 문자열.
        // toArray : List형태로 넘어온것 배열로 바꿔서 처리하기 위한 toArray() 함수.
        val items = listItems.toTypedArray<CharSequence>()
        // toArray 함수를 이용해서 size만큼 배열이 생성 되었다.
        listItems.toTypedArray<CharSequence>()
        builder.setItems(items) { dialog, item ->
            // TODO Auto-generated method stub
            if (item == mPariedDeviceCount) { // 연결할 장치를 선택하지 않고 '취소' 를 누른 경우.
                Toast.makeText(applicationContext, "연결할 장치를 선택하지 않았습니다.", Toast.LENGTH_LONG).show()
                finish()
            } else { // 연결할 장치를 선택한 경우, 선택한 장치와 연결을 시도함.
                connectToSelectedDevice(items[item].toString())
            }
        }
        builder.setCancelable(false) // 뒤로 가기 버튼 사용 금지.
        val alert = builder.create()
        alert.show()
    }

    fun checkBluetooth() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (mBluetoothAdapter == null) {  // 블루투스 미지원
            Toast.makeText(applicationContext, "기기가 블루투스를 지원하지 않습니다.", Toast.LENGTH_LONG).show()
            finish() // 앱종료
        } else { // 블루투스 지원
            if (!mBluetoothAdapter!!.isEnabled) { // 블루투스 지원하며 비활성 상태인 경우.
                Toast.makeText(applicationContext, "현재 블루투스가 비활성 상태입니다.", Toast.LENGTH_LONG).show()
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            } else  // 블루투스 지원하며 활성 상태인 경우.
                selectDevice()
        }
    }

    override fun onDestroy() {
        try {
            mWorkerThread!!.interrupt() // 데이터 수신 쓰레드 종료
            mInputStream!!.close()
            mSocket!!.close()
        } catch (e: Exception) {
        }
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            REQUEST_ENABLE_BT -> if (resultCode == RESULT_OK) { // 블루투스 활성화 상태
                selectDevice()
            } else if (resultCode == RESULT_CANCELED) { // 블루투스 비활성화 상태 (종료)
                Toast.makeText(applicationContext, "블루투수를 사용할 수 없어 프로그램을 종료합니다", Toast.LENGTH_LONG)
                    .show()
                finish()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        // 사용자 정의 함수로 블루투스 활성 상태의 변경 결과를 App으로 알려줄때 식별자로 사용됨 (0보다 커야함)
        const val REQUEST_ENABLE_BT = 10
    }
}