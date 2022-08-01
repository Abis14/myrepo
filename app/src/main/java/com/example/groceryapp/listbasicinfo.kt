package com.example.groceryapp

import java.util.HashMap

data class listbasicinfo(var title:String?="",var color:String?="", var listdetails:HashMap<String, listdetails>? = HashMap()) {


    fun toMap(): Map<String, Any?> {

        return mapOf<String, Any?>(
            "title" to title,
            "color" to color,
            "listdetails" to listdetails
        )
    }


}