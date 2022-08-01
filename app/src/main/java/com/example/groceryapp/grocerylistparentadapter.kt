package com.example.groceryapp

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import java.util.HashMap
import kotlin.collections.ArrayList

class grocerylistparentadapter(var dataset: HashMap<String, ArrayList<listdetails>>,var listbasicinfo: listbasicinfo,var Adapter:Adaptercallback,var c:Context): RecyclerView.Adapter<grocerylistparentadapter.ViewHolder>() {
    var isselected:Boolean=false
    var counters:Int=0
    var selecteditem:ArrayList<String> = ArrayList()
    lateinit var childadapter: gocerylistchildadapter
    class ViewHolder(val view:View): RecyclerView.ViewHolder(view) {
val title:TextView
val childrecy:RecyclerView


        //var list:ArrayList<String> = ArrayList()

        init {
    title=view.findViewById(R.id.textView6)
    childrecy=view.findViewById(R.id.grocerylistchildrec)
    view.setOnClickListener {

        //list.add()

    }
}

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.grocerylistparentcard,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        var childlist: java.util.ArrayList<listdetails> = java.util.ArrayList()
//        Log.d("mylist", dataset[position].listdetails.toString())
//        dataset[position].listdetails?.forEach {
//            childlist.add(it.value)

        var key:MutableList<String>
        key=dataset.keys.toMutableList()

        Log.d("jjjj",key.toString())
        var categorylis: java.util.ArrayList<String> = java.util.ArrayList()

if(key[position].toString()=="Done")
{
    listbasicinfo.listdetails?.forEach {
        if(it.value.done=="true") {
            dataset.get("Done")?.add(it.value)
            holder.title.text = key[position].toString()
             childadapter =
                gocerylistchildadapter(dataset.getValue(key[position]), listbasicinfo.title!!, Adapter)
            holder.childrecy.adapter = childadapter
            holder.childrecy.layoutManager =
                LinearLayoutManager(holder.childrecy.context, LinearLayoutManager.VERTICAL, false)
        }

    }
}
        else {
    holder.title.text = key[position].toString()
    childadapter =
        gocerylistchildadapter(dataset.getValue(key[position]), listbasicinfo.title!!,Adapter)
    holder.childrecy.adapter = childadapter
    holder.childrecy.layoutManager =
        LinearLayoutManager(holder.childrecy.context, LinearLayoutManager.VERTICAL, false)
}
holder.title.setOnLongClickListener {
    if(isselected==true)
    {
        if(selecteditem.contains(key[position])) {
            selecteditem.remove(key[position])
            counters--
            holder.title.setBackgroundColor(Color.parseColor("#ffffff"))
            //counter(counters)
Adapter.onchange(-1)
        }
        else
        {
            selecteditem.add(key[position])
            counters++
            Adapter.onchange(1)
            holder.title.setBackgroundColor(Color.parseColor("#FFFFF7"))
        }


    }
    else
    {
        isselected=true
        counters++
        Adapter.onchange(1)
        holder.title.setBackgroundColor(Color.parseColor("#FFFFF7"))
        selecteditem.add(key[position])


        //counter(counters)
//        val grocerylist:grocerylist= c as grocerylist
//        grocerylist.supportActionBar?.title= counters.toString()
//        grocerylist.  supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    true
}
        }


    override fun getItemCount(): Int {
   return dataset.size
    }
    fun getlistchild():ArrayList<String>
    {
        return childadapter.getlist()
    }
    fun getparentlist():ArrayList<String>
    {
        return selecteditem
    }
    public interface Adaptercallback
    {
        public fun onchange(count:Int)
        public fun oncancel(title:String,color:String)
    }



}