package com.acaandroid.clinicinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.list -> {
                    Toast.makeText(this, "sds", Toast.LENGTH_SHORT).show()
                }
                R.id.add -> {
                    Toast.makeText(this, "sds", Toast.LENGTH_SHORT).show()
                }
                R.id.dataBase -> {
                    Toast.makeText(this, "sds", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}