package com.example.gonggam

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MenuItem
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gonggam.databinding.ActivityStopWatchBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class StopWatchActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference


    // 뒤로가지 버튼을 누른 시각을 저장하는 속성
    var initTime = 0L

    // 버튼 상태 바꾸기 0:일시정지, 1:측정
    var btn_cur = 1

    // 멈춘 시각을 저장하는 속성
    var pauseTime = 0L

    // 측정된 시간 (초)
    var second = 0

    // 디비에 넣을 때 사용할 시간 변수들
    var watchtimed : Int = 0
    var aitimed : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_stop_watch)
        val binding = ActivityStopWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.chronometerWatch.setFormat("H:MM:SS")

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        val user = auth.currentUser


        // 이 화면에 들어오는 순간부터 측정 시작, btnsetting 1
        binding.chronometerWatch.base = SystemClock.elapsedRealtime() + pauseTime
        binding.chronometerWatch.start()
        binding.btnWatch.setText("측정중")
        binding.btnWatch.setTextColor(Color.parseColor("#0038FF"))
        btn_cur = 0

        // 디비에 있는 값 가져오기
        database.child("total_time").child(user!!.uid).child("watch_time").addValueEventListener( object : ValueEventListener {

            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot.getValue()
                watchtimed = value.toString().toInt()
                Log.i("0 watchtimed","$watchtimed")
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("스톱워치 시간","Failed to read watch_time data.")
            }

        })
        database.child("total_time").child(user!!.uid).child("ai_time").addValueEventListener( object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot.getValue()
                aitimed = value.toString().toInt()
                //Log.i("0 aitimed","$aitimed")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ai인증 시간","Failed to read ai_time data.")
            }
        })

        binding.btnWatch.setOnClickListener {
            when (btn_cur) {
                0 -> {
                    pauseTime = binding.chronometerWatch.base - SystemClock.elapsedRealtime()
                    binding.chronometerWatch.stop()
                    second = -pauseTime.toInt()/1000
                    Toast.makeText(this, "측정된 시간 : " + second + "초", Toast.LENGTH_SHORT)
                        .show()
                    //binding.btnWatchStart.isEnabled = true
                    //binding.btnWatchStop.isEnabled = false
                    binding.btnWatch.setText("일시정지")
                    binding.btnWatch.setTextColor(Color.parseColor("#FF0000"))
                    //database.child("total_time").child(user!!.uid).child("watch_time").setValue(watchtimed+second)
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



    // 툴바의 뒤로가기 버튼을 눌렀을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val user = auth.currentUser

            when(item.itemId) {
                android.R.id.home -> {
                    // DB 속 데이터 가져오기
                    database.child("total_time").child(user!!.uid).child("watch_time").addValueEventListener( object : ValueEventListener {

                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot.getValue()
                            watchtimed = value.toString().toInt()
                            Log.i("0 watchtimed","$watchtimed")
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.e("스톱워치 시간","Failed to read watch_time data.")
                        }

                    })

                    database.child("total_time").child(user!!.uid).child("ai_time").addValueEventListener( object : ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot.getValue()
                            aitimed = value.toString().toInt()
                            //Log.i("0 aitimed","$aitimed")
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Log.e("ai인증 시간","Failed to read ai_time data.")
                        }
                    })

                    val update_watch = watchtimed + second
                    val update_total = update_watch + aitimed
                    Log.i("디비에 저장할 것","$watchtimed -> $update_watch , $update_total, $second")
                    // DB에 시간 다시 저장
                    database.child("total_time").child(user!!.uid).child("watch_time").setValue(update_watch)
                    database.child("total_time").child(user!!.uid).child("total_time").setValue(update_total)
                    val intent1 = Intent(this,MainActivity::class.java)
                    startActivity(intent1)
                }

            }

        return super.onOptionsItemSelected(item)
    }
}