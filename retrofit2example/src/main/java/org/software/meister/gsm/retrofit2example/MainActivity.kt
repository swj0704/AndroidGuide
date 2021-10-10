package org.software.meister.gsm.retrofit2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.software.meister.gsm.retrofit2example.model.MealResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Date
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {      // onClick 지정
            val retrofitHelper = RetrofitHelper.getInstance()       // 레트로핏 헬퍼 객체 생성 (인스턴스화)
            val retrofit = RetrofitHelper.getRetrofit(retrofitHelper)   // 레트로핏 객체 생성
            val api = RetrofitHelper.getAPI(retrofit)               // api 생성

            val date = LocalDateTime.now()                          // 현재 시간을 LocalDateTime 타입으로 가져오기

 //           val ymd = date.dayOfYear.toString() + date.monthValue.toString() + date.dayOfMonth.toString()     // 현재 날짜 이어붙이기

            val ymd = "20211012"
            api.getMeal(ymd = ymd).enqueue(object : Callback<MealResponse>{
                override fun onResponse(
                    call: Call<MealResponse>,
                    response: Response<MealResponse>
                ) {
                    if(response.isSuccessful){      // 통신이 성공했을 때
                        if(response.body() != null) {       // 받은 객체가 null이 아닐 때
                            if(response.body()!!.mealServiceDietInfo.size == 2) {       //mealServiceDietInfo 는 head 와 row 가 있는데 row가 있는 경우
                                val text = (response.body()!!.mealServiceDietInfo[1].row?.get(0)?.DDISH_NM
                                    ?: "급식이 없습니다").split("<br/>")                     // 급식 정보가 담긴 텍스트를 <br/> 을 기준으로 나눠서 리스트에 저장
                                var mealText = ""
                                text.forEach{
                                    mealText = mealText + it + "\n"                                 // 리스트에 있던 텍스트들 줄바꿈 해서 이어 붙이기
                                }
                                findViewById<TextView>(R.id.content_text).text = mealText           // 텍스트 넣기
                            } else {
                                findViewById<TextView>(R.id.content_text).text = "급식이 없습니다"
                            }
                        }
                    } else {
                        Log.d("CODE", response.code().toString())                               // 오류 코드 출력
                    }
                }

                override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                    t.printStackTrace()             // 오류 코드 출력
                }

            })
        }

    }
}