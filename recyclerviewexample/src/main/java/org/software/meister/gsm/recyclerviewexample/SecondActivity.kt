package org.software.meister.gsm.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val title = findViewById<TextView>(R.id.second_title)
        val text = findViewById<TextView>(R.id.second_text)
        val button = findViewById<Button>(R.id.second_button)

        val position = intent.getIntExtra("position", 0)?:0

        title.text = DataObject.data[position].title
        text.text = DataObject.data[position].text

        button.setOnClickListener {
            finish()
        }
    }
}