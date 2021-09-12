package com.wonjoonshin.kotlinexample

class ClassExample {
    // 클래스란?
    // 객체를 만드는 틀
    // 객체란?
    // 물리적으로 존재하거나 추상적으로 생각할 수 있는 것 중에서 자신의 속성을 가지고 있고 다른것과 식별 가능한 것을 말한다.

    // 객체지향의 특성 3가지
    // 1. 캡슐화
    // 2. 상속
    // 3. 다형성

    //https://jwprogramming.tistory.com/121

    // 클래스의 구성요소 (필드 변수(상수), 메서드(함수))

    var a = 0

    fun function() : Int{
        return a
    }
}

fun main() {
    val classExample : ClassExample = ClassExample()        //클래스는 타입이 될 수 있다.       틀(class)를 이용해 객체를 만드는 것을 -> 인스턴스화 라고 한다

    classExample.a                                          //클래스의 필드, 메서드 등을 변수를 만들어 사용할 수 있다.
    classExample.function()


}

// 클래스는 각각의 생성자를 가지고 있다.
// 생성자는 인스턴스화 될때 호출된다.
// 생성자를 이용해 클래스에서 사용할 변수를 다른곳에서 지정해줄 수 있다.
// 생성자의 갯수는 제한이 없다. 대신 각각 연관이 있어야 한다

class ConstructorExampleOne(){              //빈 생성자

}

class ConstructorExampleTwo(var a : Int){   // var를 붙여 필드 변수로 사용할 수 있다.

}

class ConstructorExampleThree(a : Int){     // 이럴때는 이 변수를 사용하고 싶으면 따로 필드에 넣어주어야 한다
    var a1 = a
}

class ConstructorExampleFour{               //constructor 키워드를 사용하여 생성자를 만들어준다
    var a : Int = 0
    constructor(a : Int){
        this.a = a
    }
}

class ConstructorExampleFive(){             // 기본 생성자
    var a : Int = 0
    constructor(a : Int) : this(){          // this()를 이용해 기본 생성자도 호출합니다
        this.a = a                          // 이 객체의 필드 a에 매개변수 a를 넣어줍니다.
    }
}


//접근 제한자란?
//접근제한자는 객체의 필드와 메소드의 사용범위를 제한함으로써 외부로부터 보호합니다.