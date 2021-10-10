package org.software.meister.gsm.retrofit2example

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelper {
    companion object {      //자바 static과 같음

        private val BASE_URL = "https://open.neis.go.kr/hub/"               // 기본 서버 url

        fun getInstance(): RetrofitHelper {
            return RetrofitHelper()                                         // 싱글턴 객체 생성 (싱글턴 : 앱에서 오직 하나만 생성되는 객체)
        }

        fun getRetrofit(instance : RetrofitHelper) : Retrofit{
            return instance.retrofit                                        // 레트로핏 반환
        }

        fun getAPI(rt: Retrofit) : API {
            return rt.create(API::class.java)                               // API 반환
        }
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()                                                            // OkHttpClient 객체 생성

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()                                                            // 레트로핏 객체 생성

}