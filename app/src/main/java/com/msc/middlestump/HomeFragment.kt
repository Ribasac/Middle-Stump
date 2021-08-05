package com.msc.middlestump

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {

    val pid = arrayListOf<String>()
    val pname = arrayListOf<String>()
    val pcost = arrayListOf<String>()
    val pmrp = arrayListOf<String>()
    val pwholesale = arrayListOf<String>()
    val filenumber = arrayListOf<String>()

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

        var db = DatabaseHelper(fcontext)

        displayData(fcontext, db)

        val recyclerView = homeView.findViewById<RecyclerView>(R.id.productRecycler)
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = ProductAdapter(fcontext, pid, pname, pcost, pmrp, pwholesale, filenumber)
        recyclerView.setHasFixedSize(true)

        return homeView

    }

    override fun onStop() {
        super.onStop()
        pid.clear()
        pname.clear()
        pcost.clear()
        pmrp.clear()
        pwholesale.clear()
        filenumber.clear()

    }

    fun displayData(fcontext: Context, db: DatabaseHelper)
    {
        val cursor: Cursor = db.readAllData()
        if (cursor.count == 0)
        {
            Toast.makeText(fcontext, "No Data, Add products from Add product page", Toast.LENGTH_SHORT).show()
        }
        else
        {
            while (cursor.moveToNext())
            {
                pid.add(cursor.getString(0))
                pname.add(cursor.getString(1))
                pcost.add(cursor.getString(2))
                pmrp.add(cursor.getString(3))
                pwholesale.add(cursor.getString(4))
                filenumber.add(cursor.getString(5))
            }
        }
    }

}