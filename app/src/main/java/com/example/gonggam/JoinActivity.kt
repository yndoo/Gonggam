package com.example.gonggam

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference

class JoinActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private lateinit var database: DatabaseReference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        auth = FirebaseAuth.getInstance()

        val join: Button = findViewById(R.id.btn_join)
        val gotologin: Button = findViewById((R.id.btn_gotologin))
        val joinIntent = Intent(this,LoginActivity::class.java)
        val joinID:EditText = findViewById(R.id.et_join_id)
        val joinPW :EditText = findViewById(R.id.et_join_pw)
        val joinName:EditText = findViewById(R.id.et_join_name)

        join.setOnClickListener {
            // 회원가입시
            createUser(joinID.text.toString().trim(), joinPW.text.toString().trim(),joinName.text.toString().trim())

            // 로그인 화면으로 이동
            startActivity(joinIntent)
        }
        gotologin.setOnClickListener {
            // 로그인 화면으로 돌아가기
            startActivity(joinIntent)
        }
    }

    private fun createUser(email: String, password: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser

                    database.child("users").child(user!!.uid).setValue(name)
                    updateUI(user)
                }else {
                    Toast.makeText(this, "회원가입 실패1", Toast.LENGTH_SHORT).show()
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    updateUI(null)
                }
            }
            .addOnFailureListener{
                Toast.makeText(this, "회원가입 실패2", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        user?.let{
            Toast.makeText(this, "Email:${user.email}, Uid : ${user.uid}", Toast.LENGTH_SHORT).show()
        }
    }

}