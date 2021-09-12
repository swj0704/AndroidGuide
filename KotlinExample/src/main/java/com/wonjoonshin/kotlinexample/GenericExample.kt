package com.wonjoonshin.kotlinexample

class GenericExample<T> {           // T에는 아무런 타입이 들어갈 수 있다.
    var t : T? = null

    fun genericT(mT : T){
        t = mT
    }
}

fun main() {
    val genericExample1 = GenericExample<String>()           // <> 안에 String을 넣어줌으로써 t의 타입을 String으로 바꿔줌

    genericExample1.genericT("안녕")                      // 매개변수 타입이 T였는데 String으로 바꿔줬으므로 String 타입의 객체가 들어감

    println(genericExample1.t)                               // 안녕이라는 String 타입의 객체 출력

    val genericExample2 = GenericExample<Int>()              // <> 안에 Int를 넣어줌으로써 t의 타입을 Int로 바꿔줌

    genericExample2.genericT(2)                         // t의 타입이 Int 이므로 Int 타입 객체가 들어감

    print(genericExample2.t)                                // 2가 출력됨
}

