package com.example.gonggam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.vyw.tflite.AICamera
import com.example.vyw.tflite.ObjectDetection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.text.DecimalFormat

class CertifyFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    // 디비에 넣을 때 사용할 시간 변수들
    var watchtimed : Int = 0
    var aitimed : Int = 0
    var totaltimed = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        val user = auth.currentUser

        // DB 속 데이터 가져오고 total_time을 update
        database.child("total_time").child(user!!.uid).child("watch_time").addValueEventListener( object : ValueEventListener {

            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot.getValue()
                watchtimed = value.toString().toInt()
                Log.i("처음 가져옴 watchtimed","$watchtimed")
                //database.child("total_time").child(user!!.uid).child("watch_time").setValue(watchtimed)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("스톱워치 시간","Failed to read watch_time data.")
            }

        })

        database.child("total_time").child(user!!.uid).child("ai_time").addValueEventListener( object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot.getValue()
                aitimed = value.toString().toInt()
                //database.child("total_time").child(user!!.uid).child("ai_time").setValue(aitimed)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ai인증 시간","Failed to read ai_time data.")
            }
        })

        database.child("total_time").child(user!!.uid).child("total_time").addValueEventListener( object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot.getValue()
                totaltimed = value.toString().toInt()
                database.child("total_time").child(user!!.uid).child("total_time").setValue(aitimed+watchtimed)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ai인증 시간","Failed to read ai_time data.")
            }
        })
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_certify, container, false)

        val intent1 = Intent(activity,StopWatchActivity::class.java)
        val intent2 = Intent(activity, CameraActivity::class.java)
        val intent3 = Intent(activity, AICamera::class.java)

        val stopwatch: Button = view.findViewById(R.id.btn_stopwatch)
        val camera: Button = view.findViewById(R.id.cameraButton)
        val ai: Button = view.findViewById(R.id.AIButton)
        val time: TextView = view.findViewById(R.id.tv_totaltime)


        val df = DecimalFormat("00")

        var hh=0
        var mm=0
        var ss=0

        val user = auth.currentUser
        database.child("total_time").child(user!!.uid).child("total_time").addValueEventListener( object : ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val value = datasnapshot.getValue()
                totaltimed = value.toString().toInt()
                Log.i("인증탭 totaltimed 1","$totaltimed")
                // Total Time에서 시, 분, 초 추출 후 입력
                mm = totaltimed/60
                hh = mm/60
                mm = mm - hh*60
                ss = totaltimed - hh*3600 - mm*60
                time.setText(String.format("%02d:%02d:%02d", hh, mm, ss))
                Log.i("인증탭 totaltimed 2","$totaltimed, $hh, $mm, $ss")
            }

            override fun onCancelled(error: DatabaseError) {
                println("Failed to read total_time data.")
            }
        })




        //Log.i("인증탭 시간이 왜 안 나와","$totaltimed, $hh, $mm, $ss")
        //time.setText("$hh:$mm:$ss")



        stopwatch.setOnClickListener {
            startActivity(intent1)
        }
        camera.setOnClickListener {
            startActivity(intent2)
        }
        ai.setOnClickListener {
            startActivity(intent3)
            Toast.makeText(requireContext(),"AI 버튼 누름", Toast.LENGTH_SHORT).show()
        }

        return view
    }

}