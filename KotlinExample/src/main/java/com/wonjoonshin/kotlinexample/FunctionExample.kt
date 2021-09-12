package com.wonjoonshin.kotlinexample

fun main() {
    print(plus(1, 2))   //3
    print(minus(b = 3))       //2
    printTest()               //TEST
}

fun plus(a : Int, b : Int) : Int{
    return a + b
}

fun minus(a : Int = 1, b : Int) : Int{
    return b - a
}

fun printTest(){
    print("TEST")
}

// 함수는 위와 같이 반복되는 코드를 한곳에 모아 사용하는데 용이하다
// 함수는 fun 머릿말을 사용하고 다음에는 이름과 ()를 넣는데, () 안에는 매개 변수라는 함수 안에서 사용할 수 있는 변수를 넣어준다.
// 매개변수 선언 방식은 일반 변수와 유사하다. var이 빠지고 a : Int 라 해도 되며, a : Int = 0과 같이 미리 초기화를 하여도 된다.
// 함수를 사용할 때는 위의 minus(b = 3) 과 같이 특정 매개변수에 값을 정해줄 수 있다. 즉, 코틀린에서는 매개변수의 순서가 크게 중요하지 않다.
// 또한 minus 함수에서 매개변수에 a : Int = 1로 선언해 주었으므로, a의 값을 따로 넣어주지 않아도 된다. minus(b=3) 이 오류가 나지 않는 이유이다.
// 이렇게 fun 이름(매개변수) 이후에는 타입을 붙여주게 된다. fun name(variable : Type) : Type 식으로 함수의 반환 값의 타입을 정하며, 만약 반환 값이 없다면
// fun name(variable : Type) 까지만 쓰고 함수를 만들면 된다.