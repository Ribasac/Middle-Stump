package com.msc.middlestump

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView

class AddProductFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val frView: View = inflater.inflate(R.layout.fragment_add_product, container, false)

        val imgProduct : ImageView = frView.findViewById(R.id.productImageEnter)

        val getImage = registerForActivityResult(ActivityResultContracts.GetContent()){ uri: Uri ->

            imgProduct.setImageURI(uri)

        }

        imgProduct.setOnClickListener {

            getImage.launch("image/*")

        }

        return frView
        

    }




}