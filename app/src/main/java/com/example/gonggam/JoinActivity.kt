package com.example.gonggam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class JoinActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val join: Button = findViewById(R.id.btn_join)
        val gotologin: Button = findViewById((R.id.btn_gotologin))
        val joinIntent = Intent(this,LoginActivity::class.java)

        join.setOnClickListener {
            // 회원가입시 로그인 화면으로 이동
            startActivity(joinIntent)
        }
        gotologin.setOnClickListener {
            startActivity(joinIntent)
        }
    }
}