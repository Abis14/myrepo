package com.example.groceryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {
    lateinit var auth:FirebaseAuth
 var user: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth=FirebaseAuth.getInstance()
        user=auth.currentUser
        Handler(Looper.getMainLooper()).postDelayed({
            if(user!=null)
            {
                val intent=Intent(this@MainActivity,Showlist::class.java)
                startActivity(intent)
            }
            else {
                val signin = Intent(this, SIGNIN::class.java)
                startActivity(signin)
            }
        }, 3000)


    }
}