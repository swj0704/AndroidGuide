package org.software.meister.gsm.recyclerviewexample

data class DummyData(                   //테스트를 위한 실험 데이터
    val title : String,
    val text : String,
    val time : Long = System.currentTimeMillis()
)