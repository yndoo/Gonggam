package com.example.gonggam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login: Button = findViewById(R.id.btn_login)
        val gotojoin: Button = findViewById(R.id.btn_gotojoin)
        val loginID: EditText = findViewById(R.id.et_login_id)
        val loginPW: EditText = findViewById(R.id.et_login_pw)

        auth = Firebase.auth

        val intent1 = Intent(this, MainActivity::class.java)
        val intent2 = Intent(this, JoinActivity::class.java)


        login.setOnClickListener {
            //로그인 버튼을 누르면
            signIn(loginID.text.toString(), loginPW.text.toString())
            //startActivity(intent1)
        }

        gotojoin.setOnClickListener {
            // 아직 계정이 없다면?
            startActivity(intent2)
        }
    }

    private fun signIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {
                        task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
                            moveMainPage(auth.currentUser)
                        } else {
                            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
        } else {
            Toast.makeText(this, "에엥", Toast.LENGTH_SHORT).show()
        }
    }

    fun moveMainPage(user:FirebaseUser?) {
        if(user!=null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}