package com.acaandroid.clinicinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.acaandroid.clinicinfo.fragments.HomeFragment
import com.acaandroid.clinicinfo.fragments.RegisterFragment
import com.acaandroid.clinicinfo.fragments.SearchingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val registerFragment = RegisterFragment()
        val searchFragment = SearchingFragment()

        makeCurrentFragment(homeFragment)


        //button navigates fragments
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.register -> makeCurrentFragment(registerFragment)
                R.id.search -> makeCurrentFragment(searchFragment)

            }
            true
        }


    }

    //Navigate fragments
    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()

        }

}