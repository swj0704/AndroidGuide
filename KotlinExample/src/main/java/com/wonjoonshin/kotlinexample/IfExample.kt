package com.wonjoonshin.kotlinexample

fun main() {
    var i = 0

    if(i == 0){
        print(i)
    } else{
        print("else")
    }
    // i가 0이므로 i 안에 있는 숫자가 print 된다

    i = 1

    if (i == 0){
        print(i)
    } else if (i == 1){
        print("i is 1")
    }
    else {
        print("else")
    }

    // i 가 1이므로 i is 1이 print 된다

    i = 2

    if (i == 0){
        print(i)
    } else if (i == 1){
        print("i is 1")
    }
    else {
        print("else")
    }

    // i 가 2이므로 else 가 print 된다
}