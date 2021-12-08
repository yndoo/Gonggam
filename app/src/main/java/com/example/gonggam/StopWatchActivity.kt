package com.example.gonggam

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gonggam.databinding.ActivityStopWatchBinding

class StopWatchActivity : AppCompatActivity() {

    // 뒤로가지 버튼을 누른 시각을 저장하는 속성
    var initTime = 0L

    // 버튼 상태 바꾸기 0:일시정지, 1:측정
    var btn_cur = 1

    // 멈춘 시각을 저장하는 속성
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_stop_watch)
        val binding = ActivityStopWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.chronometerWatch.setFormat("H:MM:SS")

        // 이 화면에 들어오는 순간부터 측정 시작, btnsetting 1
        binding.chronometerWatch.base = SystemClock.elapsedRealtime() + pauseTime
        binding.chronometerWatch.start()
        binding.btnWatch.setText("측정중")
        binding.btnWatch.setTextColor(Color.parseColor("#0038FF"))
        btn_cur = 0

        binding.btnWatch.setOnClickListener {
            when (btn_cur) {
                0 -> {
                    pauseTime = binding.chronometerWatch.base - SystemClock.elapsedRealtime()
                    binding.chronometerWatch.stop()
                    Toast.makeText(this, "측정된 시간 : " + -pauseTime / 1000 + "초", Toast.LENGTH_SHORT)
                        .show()
                    //binding.btnWatchStart.isEnabled = true
                    //binding.btnWatchStop.isEnabled = false
                    binding.btnWatch.setText("일시정지")
                    binding.btnWatch.setTextColor(Color.parseColor("#FF0000"))
                    btn_cur = 1
                }
                1 -> {
                    binding.chronometerWatch.base = SystemClock.elapsedRealtime() + pauseTime
                    binding.chronometerWatch.start()
                    // 버튼 표시 여부
                    //binding.btnWatchStart.isEnabled = false
                    //binding.btnWatchStop.isEnabled = true
                    binding.btnWatch.setText("측정중")
                    binding.btnWatch.setTextColor(Color.parseColor("#0038FF"))
                    btn_cur = 0
                }
                else -> {
                    binding.chronometerWatch.base = SystemClock.elapsedRealtime() + pauseTime
                    binding.chronometerWatch.start()
                    binding.btnWatch.setText("측정중")
                    binding.btnWatch.setTextColor(Color.parseColor("#0038FF"))
                }
            }
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.bar_stopwatch)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //supportActionBar!!.setHomeAsUpIndicator(R.drawable.backbutton)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

    }

    /*
    // 뒤로가기 버튼 이벤트핸들러
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 뒤로가기 버튼을 눌렀을 때 처리
        if (keyCode == KeyEvent.KEYCODE_BACK){
            // 뒤로가기 버튼을 처음 눌렀거나 누른 지 3초 지났을 때 처리
            if (System.currentTimeMillis()-initTime >3000) {
                Toast.makeText(this, "종료하려면 한 번 더 누르세요", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

     */


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