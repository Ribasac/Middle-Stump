package com.msc.middlestump

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val homeView: View = inflater.inflate(R.layout.fragment_home, container, false)
        val fcontext : Context = container!!.context
        val myDataset = Datasource().loadProducts()
        val recyclerView = homeView.findViewById<RecyclerView>(R.id.productRecycler)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = ProductAdapter(fcontext, myDataset)
        recyclerView.setHasFixedSize(true)

        return homeView

    }




}