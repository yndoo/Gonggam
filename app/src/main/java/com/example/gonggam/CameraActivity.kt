package com.example.gonggam

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        // 툴바 뒤로가기 보이게하는 코드
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.bar_camera)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //supportActionBar!!.setHomeAsUpIndicator(R.drawable.backbutton)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

    }

    // 툴바의 뒤로가기 버튼을 눌렀을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                val intent1 = Intent(this,MainActivity::class.java)
                startActivity(intent1)
            }

        }
        return super.onOptionsItemSelected(item)
    }


}