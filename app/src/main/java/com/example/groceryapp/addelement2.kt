package com.example.groceryapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class addelement2 : Fragment() {

    lateinit var titles: EditText
    lateinit var description: EditText
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var btn5: Button
    lateinit var btn2nd: Button

    lateinit var list: String
    lateinit var tex: TextView
    lateinit var card: CardView
    lateinit var color: Any
    lateinit var ref: DatabaseReference
    lateinit var auth: FirebaseAuth


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addelement2, container, false)
        titles = view.findViewById(R.id.Title)
        // description=findViewById(R.id.description)
        btn1 = view.findViewById(R.id.addtolist)
        btn2 = view.findViewById(R.id.btn1)
        btn3 = view.findViewById(R.id.btn3)
        btn4 = view.findViewById(R.id.btn4)
        btn5 = view.findViewById(R.id.btn5)
        btn2nd = view.findViewById(R.id.btn2)
        auth = FirebaseAuth.getInstance()
        tex = view.findViewById(R.id.show)
        tex.visibility = View.GONE

        list = ""
        color = ""
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
        btn2.setOnClickListener {
            color = btn2.tag

        }
        btn3.setOnClickListener {
            color = btn3.tag

        }
        btn4.setOnClickListener {
            color = btn4.tag

        }
        btn5.setOnClickListener {
            color = btn5.tag

        }
        btn2nd.setOnClickListener {
            color = btn2nd.tag
        }
        btn1.setOnClickListener {
            var Title = titles.text.toString().trim()
            val listinfo: listbasicinfo2 = listbasicinfo2(Title, color,)
            val user = auth.currentUser?.uid
val member=member(FirebaseAuth.getInstance().uid.toString(),"Admin",LocalDateTime.now().toString())
//            val desc=description.text.toString().trim()
            //use cananetation when taking data from user
            ref=FirebaseDatabase.getInstance().getReference("grocerylist")
            val id = ref.push().child("listbasicinfo").key!!
            ref.child(id).push().updateChildren(
                listinfo.toMap()).addOnSuccessListener {
                Toast.makeText(activity, "hell", Toast.LENGTH_SHORT).show()
            }
            FirebaseDatabase.getInstance().getReference("grocerylist").child("listbasicinfo").orderByChild("title").equalTo(Title).get().addOnCompleteListener {
                if(it.isSuccessful)
                {
                    it.result.children.forEach { children->
                      val id=children.key.toString()
                        FirebaseDatabase.getInstance().getReference("grocerylist").child("listbasicinfo").child(id).child("members").push().updateChildren(member.toMap())

                    }
                }
            }

            var intent = Intent(activity, listadd::class.java)

           intent.putExtra("data", Title)
         startActivity(intent)
//            ref.child(id).updateChildren(listinfo.toMap()).addOnSuccessListener {
//                Toast.makeText(activity, "Data has been saved", Toast.LENGTH_SHORT).show()
//                titles.text.clear()
//            }.addOnFailureListener {
//                Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show()
//            }
//            var intent = Intent(activity, listadd::class.java)
//
//            intent.putExtra("data", Title)
//            startActivity(intent)
//        }



        }
        return view
    }
}