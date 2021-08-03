package com.msc.middlestump

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import javax.xml.transform.Source

class AddProductFragment : Fragment() {

    var fileNo : Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val frView: View = inflater.inflate(R.layout.fragment_add_product, container, false)

        var pcontext: Context = container!!.context

        var imageBitmap: Bitmap = null!!

        val imgProduct : ImageView = frView.findViewById(R.id.productImageEnter)
        val button: Button = frView.findViewById(R.id.productButton)
        val productName : EditText = frView.findViewById(R.id.pNameEnter)
        val productCost : EditText = frView.findViewById(R.id.pCostEnter)
        val productMRP : EditText = frView.findViewById(R.id.pMRPEnter)
        val productWholesale : EditText = frView.findViewById(R.id.pWholesaleEnter)

        val getImage = registerForActivityResult(ActivityResultContracts.GetContent()){ uri: Uri ->

            imgProduct.setImageURI(uri)
            val source: ImageDecoder.Source = ImageDecoder.createSource(pcontext.contentResolver, uri)
            imageBitmap = ImageDecoder.decodeBitmap(source)

        }

        imgProduct.setOnClickListener {

            getImage.launch("image/*")

        }


        val pref : SharedPreferences = pcontext.getSharedPreferences(pcontext.packageName.toString(), Context.MODE_PRIVATE)
        fileNo = pref.getInt("fileNo", 0)

        button.setOnClickListener {

            saveImageToStorage(pcontext, imageBitmap, fileNo)

            val db : DatabaseHelper = DatabaseHelper(pcontext)
            db.addProduct(productName.text.toString().trim(), productCost.text.toString().trim()
            ,productMRP.text.toString().trim(),productWholesale.text.toString().trim(), fileNo)


            fileNo++

            val pref : SharedPreferences = pcontext.getSharedPreferences(pcontext.packageName.toString(), Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = pref.edit()
            editor.putInt("fileNo", fileNo)

        }

        return frView
        

    }


    private fun saveImageToStorage(scontext: Context, bitmap: Bitmap, fileNumber: Int)
    {
        val contextWrapper: ContextWrapper = ContextWrapper(scontext)
        val dir: File = contextWrapper.getDir("productImages", Context.MODE_PRIVATE)
        val file: File = File(dir, "product"+fileNumber.toString()+".jpg")
        if(!file.exists())
        {
            var fos : FileOutputStream? = null
            try {
                fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()
                fos.close()
            }
            catch (e: Exception)
            {
                Toast.makeText(scontext, "File save failed", Toast.LENGTH_SHORT).show()
            }
            finally {

                try {
                    fos?.close()
                }
                catch (e: Exception)
                {
                    Toast.makeText(scontext, "File save failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }




}