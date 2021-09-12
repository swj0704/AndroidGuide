package com.wonjoonshin.kotlinexample

fun main() {
    var mutableArray = mutableListOf<Int>(0,1,2,3)        //JAVA의 int[] array = [0,1,2,3]과 같음
    var arrayList = arrayListOf<Int>(0,1,2,3)        //JAVA의 int[] array = [0,1,2,3]과 같음
    var array = listOf<Int>(0,1,2,3)        //JAVA의 int[] array = [0,1,2,3]과 같음, 단, 배열 안의 값을 바꿀 수 없음

    mutableArray[1] = 3
    arrayList[1] = 3
    //array[1] = 4              //Error


    //mutable은 변하기 쉬운이라는 뜻을 가지고 있다.
    //즉, 안드로이드에서의 mutable이 붙은 것은 변하기 쉽다는 뜻이다.
    //listOf는 unMutable, 즉, 변하기 어렵다 . 따라서 위의 array[1] = 4에서 오류가 나는 것이다.
    //mutableList 나 arrayList는 mutable한 형태이다. 따라서 오류가 나타나지 않는다.

    arrayList.add(1)
    mutableArray.add(5)
    //array.add(5)          //Error

    //위와 같이 mutable한 배열은, 배열에 새로운 값을 넣는데 용이하다.
    //하지만 listOf는 정해진 후 바뀌기 어려우므로 새로운 값을 넣을 수 없다.


    var collectionArrayList = ArrayList<Int>()
    collectionArrayList = arrayList

    collectionArrayList = mutableArray as ArrayList<Int>        //캐스팅 -> 타입을 강제로 변환하는 문법 자바에서는 ((ArrayList<Int>)mutableArray)

    //위를 보면 arrayList는 ArrayList 타입을 가지고 있는 것을 알 수 있다
    //또한, mutableArray 또한 ArrayList를 가지고 있는 것을 알 수 있다.

}