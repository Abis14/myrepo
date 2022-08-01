package com.example.groceryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [categoryfragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class categoryfragment :BottomSheetDialogFragment() {
    // TODO: Rename and change types of parameters
    lateinit var categor:ArrayList<String>
    lateinit var pics:ArrayList<Any>
    lateinit var uids:ArrayList<String>
    lateinit var recy:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_categoryfragment, container, false)
        recy=view.findViewById(R.id.recyc)
        categor= ArrayList()
        pics=ArrayList()
        categor = ArrayList()
        categor.add("Fruits")
        categor.add("vegetables")
        categor.add("snacks")
        categor.add("Beverges")
        categor.add("Dairy")
        uids = ArrayList()
        pics = ArrayList()
        pics.add(R.drawable.fruits)
        pics.add(R.drawable.vegetables)

        pics.add(R.drawable.snacks)
        pics.add(R.drawable.beverges)
        pics.add(R.drawable.diary)
        val adap2 = categoryadapter(categor, pics)
        //adapter= assingedadapter(categor,pics,requireActivity(),this,uids)
        recy.adapter = adap2
        recy.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        val adapter=categoryadapter(categor,pics)

        return view
    }


}