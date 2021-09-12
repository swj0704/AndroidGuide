package com.wonjoonshin.kotlinexample

fun main() {
    var i1 = 5
    var i2 = 3

    print(i1 + i2)      //8

    i1 = 2
    i2 = 1

    print(i1 + i2)      //3

    //var 는 변수를 만들 때 사용하는 머릿말!
    //타입을 지정해주고 싶다면 var i : Int = 1
    //초기화를 늦게 해주고 싶다면 lateinit var i : Int
}