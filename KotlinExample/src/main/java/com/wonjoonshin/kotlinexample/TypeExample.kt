package com.wonjoonshin.kotlinexample

fun main() {
    var i : Int = 0             //Int 는 정수형을 나타낸다. 따라서 1,2,3 과 같은 정수를 가지고 있다.
    var l : Long = 0            //Long 도 정수형을 나타낸다. 하지만 Int형 보다는 더 큰 숫자를 나타낼 수 있다.
    var sh : Short = 0          //Short 도 정수형을 나타낸다. 하지만 Int 형 보다는 더 작은 숫자만 나타낼 수 있다.
    var b : Byte = 0            //Byte 도 정수형을 나타낸다. 하지만 -128 ~ 127까지의 숫자만 나타낼 수 있다.
    var f : Float = 0f          //Float 는 실수형을 나타낸다. 따라서 1,1.2, 2.3 과 같은 실수를 가지고 있다.
    var d : Double = 0.0        //Double 은 실수형을 나타낸다. 하지만 0.0과 같이 정수형도 실수형으로 나타내어야 한다.      Float 의 두배 가량의 메모리를 가지고 있어 더 큰 숫자를 나타내는데 용이하다
    var c : Char = 'c'          //Char 는 문자를 나타낸다. 따라서 'c', 'a' 와 같이 한 문자를 나타낼 수 있다.
    var s : String = "string"   //String 은 문자열을 나타낸다. 따라서 "String" 과 같이 문자를 나열하면 된다.
    var bo : Boolean = true     //Boolean 은 논리형을 나타낸다. 따라서 true, false 두가지의 인자만 가질 수 있다.


    // NullSafe란?
    // 타입 뒤에 ? 를 붙이면 Null을 대입할 수 있게 된다
    // var s : String? = null           //OK
    // var s : String = null            //Error

}