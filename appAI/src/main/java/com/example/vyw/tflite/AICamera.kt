package com.example.vyw.tflite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast

class AICamera : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_test)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.bar_aicamera2)
        setSupportActionBar(toolbar)
        //setHasOptionsMenu(true)
    }
    // 툴바의 '뒤로가기' 버튼을 눌렀을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_back -> {
                // '뒤로가기' 버튼 눌렀을 때
                Toast.makeText(this, "뒤로가기 click checked", Toast.LENGTH_SHORT).show();
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun btnObjectDetection_click(view: View) {
        val intent = Intent(this, ObjectDetection::class.java)
        startActivity(intent)
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
