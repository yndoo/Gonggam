package com.example.gonggam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 하단 탭이 눌렸을 때 화면을 전환하기 위해선 이벤트 처리하기 위해 BottomNavigationView 객체 생성
        var bnv_main = findViewById(R.id.bnv_main) as BottomNavigationView

        // OnNavigationItemSelectedListener를 통해 탭 아이템 선택 시 이벤트를 처리
        // navi_menu.xml 에서 설정했던 각 아이템들의 id를 통해 알맞은 프래그먼트로 변경하게 한다.
        bnv_main.run { setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.first -> {
                    // 다른 프래그먼트 화면으로 이동하는 기능
                    val liveFragment = LiveFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, liveFragment).commit()
                }
                R.id.second -> {
                    val rankingFragment = RankingFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, rankingFragment).commit()
                }
                R.id.third -> {
                    val certifyFragment = CertifyFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, certifyFragment).commit()
                }
                R.id.fourth -> {
                    val groupFragment = GroupFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, groupFragment).commit()
                }
                R.id.fifth -> {
                    val shareFragment = ShareFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fl_container, shareFragment).commit()
                }
            }
            true
        }
            selectedItemId = R.id.third
        }

    }
}