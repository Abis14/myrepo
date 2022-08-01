package com.example.groceryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.HashMap

class addelement : AppCompatActivity() {
    lateinit var  titles:EditText
    lateinit var description:EditText
lateinit var btn1:Button
lateinit var btn2:Button
    lateinit var btn3:Button
    lateinit var btn4:Button
    lateinit var btn5:Button
    lateinit var btn2nd:Button

lateinit var list:String
lateinit var tex:TextView
lateinit var card:CardView
lateinit var color:Any
lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addelement)
auth=FirebaseAuth.getInstance()

        titles = findViewById(R.id.Title)
        // description=findViewById(R.id.description)
        btn1 = findViewById(R.id.addtolist)
        btn2=findViewById(R.id.btn1)
        btn3=findViewById(R.id.btn3)
        btn4=findViewById(R.id.btn4)
        btn5=findViewById(R.id.btn5)
        btn2nd=findViewById(R.id.btn2)

        tex = findViewById(R.id.show)
        tex.visibility = View.GONE

        list = ""
        color=""
//        btn2=findViewById(R.id.addtolist)
//
//        btn2.setOnClickListener{
//
//            list+=description.text.toString()+","
//            description.text.clear()
//            tex.visibility=View.VISIBLE
//            tex.text=list
//
//        }
        btn2.setOnClickListener{
            color=btn2.background.toString()
            android.widget.Toast.makeText(this, color.toString(), Toast.LENGTH_SHORT).show()
        }
        btn3.setOnClickListener{
            color=btn3.background.toString()
            android.widget.Toast.makeText(this, color.toString(), Toast.LENGTH_SHORT).show()
        }
        btn4.setOnClickListener{
            color=btn4.background.toString()
            android.widget.Toast.makeText(this, color.toString(), Toast.LENGTH_SHORT).show()
        }
        btn5.setOnClickListener{
            color=btn5.background.toString()
            android.widget.Toast.makeText(this, color.toString(), Toast.LENGTH_SHORT).show()
        }
        btn2nd.setOnClickListener{
            color=btn2nd.background.toString()
        }
        btn1.setOnClickListener {
         var Title=titles.text.toString().trim()+","
//            val desc=description.text.toString().trim()
            //use cananetation when taking data from user
            Title+=color

            var intent = Intent(this, listadd::class.java)
            intent.putExtra("data",Title)
            startActivity(intent)
Log.d("data",Title)

            //databaseModel.description?.split(",")?.toList()

//            val Data= info(Title,desc)

/*val path="Data"
            var users= FirebaseAuth.getInstance().currentUser?.uid
           val database = Firebase.database.getReference("Users").child(users!!)
            val id=database.push().child("Users").key!!

            Log.d("TAG", "onCreate: Newly created key $id")


            val dataMap = HashMap<String, Any>()
            dataMap["Test"] = "asdfasdfasdfsdafasdf"
            dataMap["asdfasdfasd"] = 123123

          database.child(id).updateChildren(databaseModel.toMap()) .addOnSuccessListener {
             Toast.makeText(this, "Data has been saved", Toast.LENGTH_SHORT).show()
//             titles.text.clear()
//              description.text.clear()
           }.addOnFailureListener{

               Toast.makeText(this, "Unable to store data sorry try again", Toast.LENGTH_SHORT).show()
           }
       }
    }*/

        }
    }}