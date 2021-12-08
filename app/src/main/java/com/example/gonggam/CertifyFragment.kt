package com.example.gonggam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.vyw.tflite.AICamera
import com.example.vyw.tflite.ObjectDetection

class CertifyFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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