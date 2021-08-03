package com.msc.middlestump

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        val mainFrag : HomeFragment = HomeFragment()
        val secondFrag : AddProductFragment = AddProductFragment()

        setCurrentFragment(mainFrag, "Home")

        var bottomNavigationView: BottomNavigationView = findViewById(R.id.botnav)
        bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){
                R.id.homenav->setCurrentFragment(mainFrag, "Home")
                R.id.productnav->setCurrentFragment(secondFrag,"Add Product")
            }
            true
        }

        val optionImageButton : ImageView = findViewById(R.id.optionsButton)
        optionImageButton.setOnClickListener { optionClick() }

    }

    private fun setCurrentFragment(fragment: Fragment, tagStr: String)
    {
        val fragManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, 0)
        fragmentTransaction.replace(R.id.fragmentframe, fragment, tagStr).commit()
    }

    private fun optionClick()
    {
        val bottomFragment : BottomSheetFragment = BottomSheetFragment()
        bottomFragment.show(supportFragmentManager, bottomFragment.tag)
    }
}