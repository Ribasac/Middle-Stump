package com.msc.middlestump

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import org.w3c.dom.Text

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "productRecord.db", null,1){

    val context: Context = context

    override fun onCreate(db: SQLiteDatabase?) {
        val query : String  = "CREATE TABLE productRecord(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, cost TEXT, mrp TEXT, wholesale TEXT, fileNumber TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, i: Int, i1: Int) {
        db?.execSQL("DROP TABLE IF EXISTS productRecord")
        onCreate(db)
    }

    fun addProduct(name: String, cost: String, mrp: String, wholesale: String, fileNo: Int)
    {
        val db : SQLiteDatabase = this.writableDatabase
        val cv : ContentValues = ContentValues()
        cv.put("name",name)
        cv.put("cost",cost)
        cv.put("mrp",mrp)
        cv.put("wholesale",wholesale)
        cv.put("fileNumber",fileNo)

        val result: Long = db.insert("productRecord",null,cv)

        if (result==(-1).toLong())
        {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }

}