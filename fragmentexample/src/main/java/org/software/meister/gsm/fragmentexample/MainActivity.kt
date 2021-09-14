package org.software.meister.gsm.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.frame, HomeFragment()).commit()      //FrameLayout에 Fragment를 주입해준다
        findViewById<BottomNavigationView>(R.id.navigation).selectedItemId = R.id.page_1            //BottomNavigation의 선택 현황을 설정해준다

        initView()
    }

    fun initView(){
        //아래 코드를 BottomNavigation의 xml 속성에 넣어준다.
        //app:menu="@menu/bottom_navigation"
        //이 코드를 넣는 이유는 BottomNavigation에 표시될 item을 설정해주기 위해서이다.
        //BottomNavigation의 item은 최대 5개까지 권장된다.

        findViewById<BottomNavigationView>(R.id.navigation).setOnItemSelectedListener {             //BottomNavigation의 아이템을 선택할 때 호출된다
            when(it.itemId){
                R.id.page_1 -> {                                                                    //BottomNavigation에 첫번째 아이템을 선택하면 아래의 코드가 호출된다.
                    supportFragmentManager.beginTransaction().replace(R.id.frame, HomeFragment()).commit()      //MainActivity의 frame에 HomeFragment를 넣어주는 코드.
                    true
                }
                R.id.page_2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame, MenuFragment()).commit()
                    true
                }
                R.id.page_3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame, UserFragment()).commit()
                    true
                }
                else -> false
            }

        }
    }
}