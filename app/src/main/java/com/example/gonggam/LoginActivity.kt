package com.example.gonggam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login: Button = findViewById(R.id.btn_login)
        val gotojoin: Button = findViewById(R.id.btn_gotojoin)
        val intent1 = Intent(this, MainActivity::class.java)
        val intent2 = Intent(this, JoinActivity::class.java)

        login.setOnClickListener {
            //로그인 버튼을 누르면
            startActivity(intent1)
        }

        gotojoin.setOnClickListener {
            // 아직 계정이 없다면?
            startActivity(intent2)
        }
    }
}