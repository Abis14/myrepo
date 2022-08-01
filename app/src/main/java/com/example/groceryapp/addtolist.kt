package com.example.groceryapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_addtolist.*
import java.time.LocalDateTime


/**
 * A simple [Fragment] subclass.
 * Use the [addtolist.newInstance] factory method to
 * create an instance of this fragment.
 */
class addtolist : Fragment() {

lateinit var spin:Button
lateinit var item:EditText
    lateinit var spin2:Button
lateinit var spinnerad:spinneradapter
lateinit var categorylist:ArrayList<String>
lateinit var pic:ArrayList<Any>
lateinit var spin2ad:spinneradapter
lateinit var addbtn:Button
lateinit var assignlist:ArrayList<String>
lateinit var ref:DatabaseReference
lateinit var auth:FirebaseAuth
lateinit var title:String
lateinit var imges:ArrayList<Any>
lateinit var categorym:listdetails
var ishown:Boolean=false
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        //val view=inflater.inflate(R.layout.fragment_menu, container, false)
    ): View? {
        // Inflate the layout for this fragme
        val view=inflater.inflate(R.layout.fragment_addtolist, container, false)
     spin=view.findViewById(R.id.spinner2)
        spin2=view.findViewById(R.id.spinner)
        assignlist= ArrayList()
//        assignlist.add("Assingedtoall")
     //assignlist.add("Assigntoabis")
        categorylist= ArrayList()
        item=view.findViewById(R.id.itemdetails)
        val bundle=arguments
        title=bundle!!.getString("title").toString()
Log.d("title",title.toString())

        categorylist.add("Uncategorized")
        categorylist.add("Diary")
        categorylist.add("Banana")
        categorylist.add("Beverges")
        categorylist.add("vegetable")
        categorylist.add("Fruits")
        addbtn=view.findViewById(R.id.button3)
        addbtn.setOnClickListener{
            var it=item.text.toString()

           // val category2=spin.selectedItem.toString()
           // categorym= listdetails(category2.toString())
           // val  assinged=spin2.selectedItem.toString()
           // val listdetails:listdetails2= listdetails2(it,category2,assinged)
            auth=FirebaseAuth.getInstance()
            val user=auth.currentUser?.uid
            var idy:String=""
            var id:String=""
var member=member(FirebaseAuth.getInstance().uid.toString(),"Admin",LocalDateTime.now().toString())
            FirebaseDatabase.getInstance().getReference("grocerylist").child("listbasicinfo").orderByChild("title").equalTo(title)

                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {

                        it.result.children.forEach { children ->
                            Log.d("succes","sucessy")
                             id = children.key.toString()

                            Log.d("TAG", "onCreateView: $id of child")
//                            FirebaseDatabase.getInstance().getReference("grocerylist")
//                                .child("listbasicinfo").child(id).child("members").push().updateChildren(member.toMap()).addOnSuccessListener {
//                                    Toast.makeText(activity, "List details has been saved", Toast.LENGTH_SHORT).show()
//                                }

//                            FirebaseDatabase.getInstance().getReference("grocerylist")
//                                .child("listbasicinfo").child(id).child("listdetails").push().updateChildren(listdetails.toMap()).addOnSuccessListener {
//                                    Toast.makeText(activity, "List details has been saved", Toast.LENGTH_SHORT).show()
//                                }
                        }
                    }
                    else {
                        Log.d("error", "error")
                    }
                }.addOnFailureListener {
                    Log.d("exception",it.toString())
                }

            /*val id = ref.child("listbasicinfo").orderByChild("title").equalTo("hh").addValueEventListener(object:ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(item in snapshot.children)
                    {
                        val ids =item.key.toString()
//                        idy=ids.toString()

                        val title = item.getValue()

                        FirebaseDatabase.getInstance().getReference("Users").child(user!!)
                            .child("listbasicinfo").child(ids).push().updateChildren(listdetails.toMap()).addOnSuccessListener {
                            Toast.makeText(activity, "List details has been saved", Toast.LENGTH_SHORT).show()
                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
            )*/

//            val id=ref.child("lisbasicinfo").orderByChild("title").equalTo("hh").addListenerForSingleValueEvent(object:ValueEventListener
//            {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    val key=snapshot.children.toString()
//                    Log.d("keyss",key)
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    TODO("Not yet implemented")
//                }
//
//            })

//            ref.child(idy).push().updateChildren(listdetails.toMap()).addOnSuccessListener {
//                Toast.makeText(activity, "List details has been saved", Toast.LENGTH_SHORT).show()
//            }


        }
        pic= ArrayList()
   pic.add(R.drawable.fruit)
  pic.add(R.drawable.veg)
    pic.add(R.drawable.drink)
    pic.add((R.drawable.ic_launcher_background))
      pic.add(R.drawable.veg)


        imges= ArrayList()

        Log.d("before",imges.size.toString())
        var bundles=Bundle()
        bundles= requireArguments()
        val selected=bundles?.get("selected")
        val uid=bundles?.get("uid")
spin2.setOnClickListener {
    if(!ishown) {
        val assigned = assingedfragment()
        var bundley = Bundle()
        bundley.putString("title", title)
        assigned.arguments = bundley
        val support = activity?.supportFragmentManager
        if (support != null) {
            assigned.show(support, assigned.tag)
        }

    }

}
        spin2.setText(selected.toString())

spin.setOnClickListener {
    if(!ishown) {
        Log.d("spin","spin")
        val assigned = assingedfragment()
        var bundley = Bundle()
        bundley.putString("state", "category")
        assigned.arguments = bundley
        val support = activity?.supportFragmentManager
        if (support != null) {
            assigned.show(support, assigned.tag)
        }
    }
}
        return view
    }



    }

