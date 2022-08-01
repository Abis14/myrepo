package com.example.groceryapp

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.gson.Gson

class grocerylist : AppCompatActivity(),grocerylistparentadapter.Adaptercallback {
    lateinit var Assingedtoall: Button
    var lists: String? = null
    var counters: Int = 0
    var ids:String=""
    lateinit var img: ImageView
    lateinit var cancel: ImageView
    lateinit var title: TextView
    lateinit var databaseref: DatabaseReference
    lateinit var listdetails: listbasicinfo
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var rey: RecyclerView
    lateinit var data: ArrayList<listbasicinfo>
    lateinit var adapter: grocerylistparentadapter
    private val myMap: LinkedHashMap<String, java.util.ArrayList<listdetails>> = LinkedHashMap()
    lateinit var assingedtoall: assingedtoalllist
    lateinit var delete: ImageView
    var fragmentManager = supportFragmentManager

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grocerylist)
        toolbar = findViewById(R.id.my)


        cancel = findViewById(R.id.imageView8)
        img = findViewById(R.id.action)

        title = findViewById(R.id.textView12)
        Assingedtoall = findViewById(R.id.button4)
        rey = findViewById(R.id.assingedparent)
        data = java.util.ArrayList()
        val gson: Gson = Gson()
        val list = intent.getStringExtra("mydata")
        lists = list
        delete = findViewById(R.id.delete)
        delete.setOnClickListener {
            deleteoperation()


        }
        listdetails = gson.fromJson(list, listbasicinfo::class.java)
//        toolbar.title=listdetails.title
//        toolbar.setBackgroundColor(Color.parseColor(listdetails.color))
//        supportActionBar?.setDisplayShowTitleEnabled(false)
        setSupportActionBar(toolbar)
        databaseref = FirebaseDatabase.getInstance().getReference("grocerylist")
            .child("listbasicinfo")
        databaseref.orderByChild("title")
            .equalTo(listdetails.title.toString()).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result.children.forEach { children ->
                        ids = children.key.toString()
                        Log.d("parent", ids.toString())
                    }
                }

            }
        listdetails.listdetails?.forEach {


//            myMap[it.value.category]?.add(it.value.Itemdetails.toString())
            myMap.put(it.value.category.toString(), java.util.ArrayList())


        }
        listdetails.listdetails?.forEach {

            Log.d("TAG", "onCreateViewss: ${it.value.category}")

            myMap.get(it.value.category.toString())?.add(it.value)

            assingedtoall = assingedtoalllist()
            Assingedtoall.setOnClickListener {
                Assingedtoall.setBackgroundResource(R.drawable.selectbutton)
                data.add(listdetails)
                myMap.put("Done", java.util.ArrayList())


                adapter = grocerylistparentadapter(myMap, listdetails, this, this)

                rey.adapter = adapter
                rey.layoutManager =
                    LinearLayoutManager(this@grocerylist, LinearLayoutManager.VERTICAL, false)

            }
            cancel.setOnClickListener {
                oncancel(listdetails.title.toString(), listdetails.color.toString())
            }

        }
//    fun loadfragment()
//    {
//        var fragmenttransaction=fragmentManager.beginTransaction()
//        val bundle:Bundle= Bundle()
//        bundle.putString("mydata",lists)
//        assingedtoall.arguments=bundle
//        fragmenttransaction.replace(R.id.fr2,assingedtoall)
//        fragmenttransaction.commit()
//    }

    }

    fun settoolbar() {

    }

    override fun onchange(count: Int) {
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"))
        setSupportActionBar(toolbar)
        title.visibility=View.VISIBLE
        cancel.visibility = View.VISIBLE
        supportActionBar?.title = ""
        delete.visibility = View.VISIBLE
        counters += count
        title.text = "$counters.toString() itemSelected"



    }

    override fun oncancel(titles: String, color: String) {
        toolbar.setBackgroundColor(Color.parseColor(color))
        setSupportActionBar(toolbar)
        counters=0
        cancel.visibility = View.VISIBLE
        supportActionBar?.title = titles
        img.setImageResource(R.drawable.share)
        title.visibility = View.GONE
        cancel.visibility = View.GONE
        img.setImageResource(R.drawable.delete)
        adapter = grocerylistparentadapter(myMap, listdetails, this, this)
        rey.adapter = adapter
    }

    fun deleteoperation() {

        var itemlist: ArrayList<String> = adapter.getlistchild()

        var categorylist: ArrayList<String> = adapter.getparentlist()


        var childid: String = ""


        if (categorylist.size != 0) {
            for (item in categorylist) {
                databaseref = FirebaseDatabase.getInstance().getReference("grocerylist")
                    .child("listbasicinfo")
                databaseref
                    .child(ids).child("listdetails").orderByChild("category")
                    .equalTo(item).get().addOnCompleteListener {
                        if (it.isSuccessful) {
                            it.result.children.forEach { children ->
                                val cid = children.key.toString()
                                Log.d("ids", cid)
                                FirebaseDatabase.getInstance().getReference("grocerylist")

                                    .child("listbasicinfo").child(ids).child("listdetails")
                                    .child(cid).addValueEventListener(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            for (item in snapshot.children) {
                                                item.ref.removeValue()
                                            }
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                            TODO("Not yet implemented")
                                        }

                                    })


                            }
                        } else {
                            Log.d("failed", "failes")
                        }


                    }
            }

        }

        for (item in itemlist) {
            Log.d("itemlist",itemlist.size.toString())
            databaseref = FirebaseDatabase.getInstance().getReference("grocerylist")
                .child("listbasicinfo")
            databaseref
                .child(ids).child("listdetails").orderByChild("Itemdetails")
                .equalTo(item).get().addOnCompleteListener {
                    if (it.isSuccessful) {

                        it.result.children.forEach { children ->
                            val itemid = children.key.toString()
                            Log.d("itemid",itemid)
                            FirebaseDatabase.getInstance().getReference("grocerylist")

                                .child("listbasicinfo")
                                .child(ids).child("listdetails").child(itemid)
                                .addValueEventListener(object : ValueEventListener {
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        for (data in snapshot.children) {
                                            data.ref.removeValue()
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

}
