package com.example.groceryapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.ktx.Firebase

class share : AppCompatActivity() {
    var id: String = ""
    lateinit var text: TextView
    lateinit var button: Button
    var link: String = ""
    val title=""
    var email:ArrayList<String> = ArrayList()
    var dp:ArrayList<Any> = ArrayList()
    var uid:ArrayList<String> = ArrayList()
    var fragmentmanager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)
        val title = intent.getStringExtra("title")

        FirebaseDatabase.getInstance().getReference("grocerylist").child("listbasicinfo")
            .orderByChild("title").equalTo(title).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result.children.forEach { children ->
                        id = children.key.toString()
                    }

                }


            }
Loadfragment()




//            val mydata = "hi please bring these grocery ".toString()
//            text.text = d.toString()
//
//            val action = Intent()
//            action.action = Intent.ACTION_SEND
//            action.putExtra(Intent.EXTRA_SUBJECT, mydata)
//            action.putExtra(Intent.EXTRA_TEXT, d.toString())
//            action.type = "text/plain"
//
//            startActivity(action)

    }

    fun createshareuri(id: String): Uri {
        val builder = Uri.Builder()
        builder.scheme("http")
        builder.authority("groceryapp")
            .appendPath("grocerylist")
            .appendQueryParameter("id", id)
        return builder.build()
    }

    fun dynamiclink(myuri: Uri): Uri {
        val dynamiclink = FirebaseDynamicLinks.getInstance().createDynamicLink().setLink(myuri)
            .setDomainUriPrefix("https://abis.page.link/")
            .setAndroidParameters(DynamicLink.AndroidParameters.Builder().build())
            .buildDynamicLink()
        return dynamiclink.uri
    }

    fun Loadfragment() {
        val myuri = createshareuri(id)
        link = dynamiclink(myuri).toString()
        val fragmenttransaction = fragmentmanager.beginTransaction()
        val linkfragment = linkfragment()
        val bundle = Bundle()
        bundle.putString("link", link)
        linkfragment.arguments = bundle
        fragmenttransaction.replace(R.id.framelayout, linkfragment)
        fragmenttransaction.commit()
    }
    fun gettingmember()
    {
        FirebaseDatabase.getInstance().getReference("grocerylist").child("listbasicinfo").orderByChild("title").equalTo(title.toString()).get().addOnCompleteListener {
            if(it.isSuccessful)
            {
                it.result.child("members").children.forEach { children->
                    FirebaseDatabase.getInstance().getReference("Users").addValueEventListener(object: ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            for(item in snapshot.children)
                            {
                                if(item.child("Id").value==children.child("uid").value)
                                {

                                    email.add(item.child("Email").value.toString())
                                    dp.add(item.child("img").value.toString())
                                    uid.add(item.child("Id").value.toString())

                                }
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    })
                }
            }
        }
    }
}
