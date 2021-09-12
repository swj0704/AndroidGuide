package com.wonjoonshin.kotlinexample

fun main() {
    var i = 0
    var j = 1

    when(i){
        0 -> print("zero")
        else -> print("not zero")
    }

    // 위와 같이 when은 java의 switch~case 문과 비슷한 점이 있다.
    // 하지만 switch~case에선 필수적인 break가 필요 없는 것이 장점이다.

    when{
        i == 0 -> print("zero")
        j == 1 -> print("one")
        else -> print("not both")
    }

    // 위와 같이 조건문과 연계 지어 사용할 수도 있다.
    // 위와 같은 특징 때문에 when문은 if문과 switch~case문을 합쳐놓은 것과 같다.
}