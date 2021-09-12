package com.wonjoonshin.kotlinexample

fun main() {
    var array = listOf<Int>(0,1,2,3)        //JAVA의 int[] array = [0,1,2,3]과 같음, 단, 배열 안의 값을 바꿀 수 없음

    for (a in array){
        print(a)
    }

    // java의 forEach 문이라 생각하면 된다

    array.forEach{ i ->
        print(i)
    }

    array.forEachIndexed { index, i ->
        print(i + index)
    }

    // 코틀린에서는 위와 같이 list 내부적으로 forEach나 index까지 포함하고 있는 forEachIndexed 라는 함수를 통해
    // 리스트를 이용한 코드를 여러가지 구성할 수 있다.

}