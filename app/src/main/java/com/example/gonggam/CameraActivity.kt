package com.example.gonggam

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private var preview : Preview? = null
    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        // 툴바 뒤로가기 보이게하는 코드
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.bar_camera)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //supportActionBar!!.setHomeAsUpIndicator(R.drawable.backbutton)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        // 카메라
        startCamera()

        findViewById<Button>(R.id.btn_capture).setOnClickListener {
            takePhoto()
        }
        findViewById<Button>(R.id.btn_upload).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        outputDirectory = getOutputDirectory()

        cameraExecutor = Executors.newSingleThreadExecutor()

    }

    private fun takePhoto() {
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        val user = auth.currentUser
        var pcount = 0

// Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return
// Create time-stamped output file to hold the image
        val photoFile = File(
            outputDirectory,
            newJpgFileName())
// Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
// Set up image capture listener, which is triggered after photo has
// been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.d("CameraX-Debug", "Photo capture failed: ${exc.message}", exc)
                }
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(photoFile)
                    val msg = "사진 인증이 1회 완료되었습니다: $savedUri"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    // 사진 저장 성공시 DB에서 횟수 불러오기
                    database.child("picture_count").child(user!!.uid).addValueEventListener( object :
                        ValueEventListener {
                        override fun onDataChange(datasnapshot: DataSnapshot) {
                            val value = datasnapshot.getValue()
                            pcount = value.toString().toInt()
                        }
                        override fun onCancelled(error: DatabaseError) {
                            Log.e("카메라 - 사진 인증","Failed to read picture_count data.")
                        }
                    })
                    Log.d("CameraX-Debug", msg)
                    // pcount +1 해서 다시 DB에 저장
                    pcount += 1
                    database.child("picture_count").child(user!!.uid).setValue(pcount)
                    //Toast.makeText(baseContext, "사진 인증이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
// Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
// Preview
            preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(findViewById<PreviewView>(R.id.view_cam).surfaceProvider)
                }
// ImageCapture
            imageCapture = ImageCapture.Builder()
                .build()
// Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
// Unbind use cases before rebinding
                cameraProvider.unbindAll()
// Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture)
            } catch(exc: Exception) {
                Log.d("CameraX-Debug", "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun newJpgFileName() : String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "${filename}.jpg"
    }
    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir
        else filesDir
    }
    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
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