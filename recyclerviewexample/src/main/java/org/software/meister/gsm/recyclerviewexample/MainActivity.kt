package org.software.meister.gsm.recyclerviewexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)

        recyclerView = findViewById(R.id.recyclerView)
        //recyclerView에 layoutmanager를 지정해주지 않으면 리스트가 표시되지 않으니 주의할것!!
        //layoutmanager 는 linearlayoutmanager, gridlayoutmanager를 보통 사용한다!
        //programmatically
        //recyclerView!!.layoutManager = LinearLayoutManager(this)

        DataObject.data.add(DummyData("first title", "first text"))
        DataObject.data.add(DummyData("second title", "second text"))
        DataObject.data.add(DummyData("third title", "third text"))
        DataObject.data.add(DummyData("fourth title", "fourth text"))             //데이터 추가
        DataObject.data.add(DummyData("fifth title", "fifth text"))
        DataObject.data.add(DummyData("sixth title", "sixth text"))

        recyclerView!!.adapter = MyAdapter(DataObject.data)           //RecyclerView의 adapter에 MyAdapter 넣기
        recyclerView!!.adapter!!.notifyDataSetChanged()

        button.setOnClickListener {
            startActivity(Intent(this, WriteActivity::class.java))
        }
    }

    override fun onResume() {
        if(recyclerView != null){
            if(recyclerView!!.adapter != null){
                recyclerView!!.adapter!!.notifyDataSetChanged()             //데이터의 변경점이 있으면 리스트 갱신
            }
        }
        super.onResume()
    }
}