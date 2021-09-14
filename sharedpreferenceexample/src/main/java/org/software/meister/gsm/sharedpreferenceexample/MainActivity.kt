package org.software.meister.gsm.sharedpreferenceexample

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences =
            getSharedPreferences("pref", Context.MODE_PRIVATE)      //SharedPreference 객체 생성

        if(getText() != null){
            findViewById<EditText>(R.id.editText).setText(getText().toString())     // 저장된 Text가 있다면 editText에 띄우기
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            setText(findViewById<EditText>(R.id.editText).text.toString())          // 버튼을 누르면 editText의 text가 저장
        }

    }

    fun setText(text : String){
        preferences.edit().putString("Text", text).apply()
    }

    fun getText() : String?{
        return preferences.getString("Text", null)
    }


}