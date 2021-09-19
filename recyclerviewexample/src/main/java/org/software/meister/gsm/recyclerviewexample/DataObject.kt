package org.software.meister.gsm.recyclerviewexample

object DataObject{          //앱 전체에서 사용하기 위해 object로 만들었다. (object는 JAVA의 static과 같음,
                            //단, 클래스 전체가 static이 되므로 부분적인 static을 사용하고 싶으면
                            //companion object 라는 명령어를 따로 적어주면 그 안에 있는 모든 변수와 메서드는
                            //static으로 생성이 된다.)
    val data = ArrayList<DummyData>()
}