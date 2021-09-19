package org.software.meister.gsm.recyclerviewexample

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date

class MyAdapter(val list : ArrayList<DummyData>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(val view : View) : RecyclerView.ViewHolder(view){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val title = holder.view.findViewById<TextView>(R.id.item_title)
        val text = holder.view.findViewById<TextView>(R.id.item_text)
        val time = holder.view.findViewById<TextView>(R.id.item_time)
        val button = holder.view.findViewById<Button>(R.id.item_button)

        //holder.view는 아이템 뷰를 의미한다.
        //holder.view.context는 아이템을 사용하고 있는 액티비티, 프래그먼트의 context를 가져온다.

        title.text = list[position].title
        text.text = list[position].text

        val date = Date(list[position].time)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val local : String = simpleDateFormat.format(date)

        time.text = local

        button.setOnClickListener {
            val intent = Intent(holder.view.context, SecondActivity::class.java)        //context를 이용해 intent도 사용할 수 있다.
            intent.putExtra("position", position)
            holder.view.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size                //이 갯수만큼 리사이클러뷰의 아이템이 생성된다!
    }

}