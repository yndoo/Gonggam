package com.example.gonggam

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AicameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aicamera)

        // 툴바 뒤로가기 보이게하는 코드
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.bar_aicamera)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_aicamera, menu)
        return true
    }

    // 툴바의 뒤로가기 버튼을 눌렀을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                val intent1 = Intent(this,MainActivity::class.java)
                startActivity(intent1)
            }
            R.id.item_flip -> {
                // 화면전환 버튼 눌렀을 때
                Toast.makeText(this, "click checked", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item)
    }

}