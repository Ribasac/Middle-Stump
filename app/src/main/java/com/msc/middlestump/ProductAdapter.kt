package com.msc.middlestump

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.FileInputStream

class ProductAdapter(

    private val context: Context,
    private val pid : ArrayList<String>,
    private val pname : ArrayList<String>,
    private val pcost : ArrayList<String>,
    private val pmrp : ArrayList<String>,
    private val pwholesale : ArrayList<String>,
    private val filenumber : ArrayList<String>

    ) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(private val view: View): RecyclerView.ViewHolder(view)
    {
        val textView: TextView = view.findViewById(R.id.itemName)
        val imageView: ImageView = view.findViewById(R.id.itemImage)
        val textViewmrp: TextView = view.findViewById(R.id.itemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)

        return ProductViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.textView.text = pname.get(position)
        holder.imageView.setImageBitmap(getImage(filenumber.get(position)))
        holder.textViewmrp.text = pmrp.get(position)
    }

    override fun getItemCount(): Int {
        return pid.size
    }

    fun getImage(fileNo: String): Bitmap
    {
        val contextWrapper: ContextWrapper = ContextWrapper(context)
        val dir: File = contextWrapper.getDir("productImages", Context.MODE_PRIVATE)
        val file: File = File(dir, "product"+fileNo+".jpg")
        val bm: Bitmap = BitmapFactory.decodeStream(FileInputStream(file))
        return bm
    }

}