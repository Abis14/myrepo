package com.example.groceryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class categoryadapter(var cate:ArrayList<String>,var imgs:ArrayList<Any>):
    RecyclerView.Adapter<categoryadapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
val cat:TextView
val img:ImageView
init {
    cat=view.findViewById(R.id.textView3)
    img=view.findViewById(R.id.img)
}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val view=LayoutInflater.from(parent.context).inflate(R.layout.spinneritem,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cat.text=cate[position]
        holder.img.setImageResource(imgs[position] as Int)
    }

    override fun getItemCount(): Int {
        return cate.size
    }
}