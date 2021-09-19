package org.software.meister.gsm.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class WriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        val title = findViewById<EditText>(R.id.write_title)
        val text = findViewById<EditText>(R.id.write_text)
        val button = findViewById<Button>(R.id.write_button)

        button.setOnClickListener {
            if(title.text.toString().trim() != "" && text.text.toString().trim() != "") {
                DataObject.data.add(DummyData(title.text.toString(), text.text.toString()))     //데이터 추가
                finish()
            } else {
                Toast.makeText(this, "빈칸이 있습니다", Toast.LENGTH_SHORT).show()     //빈칸이 있으면 토스트 메세지 표시
            }
        }
    }
}