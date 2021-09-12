package com.wonjoonshin.kotlinexample

fun main() {
    var i = 0

    while (i < 5){
        print(i)
        i++
    }
    //while문은 조건에 맞는다면 안의 코드를 실행하고, 조건이 한번이라도 어긋나면 그 코드를 종료한다.

    //따라서 현재 위의 while문이 종료된 이후에 i 를 0으로 바꾸어도 while문의 코드가 재실행 되지는 않는다.

    while (i == 0){
        print(i)
        i--

        if(i == 3){
            break
        }
    }

    // while문의 코드가 실행 도중 while문의 조건이 충족되지 않아도 break를 만나면 while문을 종료한다.

    //위 코드는 i를 출력하며 1씩 빼다가 i가 3이 되면 while문을 종료하는 코드이다.
}