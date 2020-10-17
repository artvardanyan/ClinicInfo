package com.acaandroid.clinicinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var fragmentRegisterPatient: RegisterPatient = RegisterPatient()
    private var fragmentListPatent: ListPatient = ListPatient()
    private var fragmentDataBasePatient: DataBasePatient = DataBasePatient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemReselectedListener { item ->
            when(item.itemId) {
                R.id.list -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, fragmentListPatent)
                        .commit()
                }
                R.id.add -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, fragmentRegisterPatient)
                        .commit()
                }
                R.id.dataBase -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment, fragmentDataBasePatient)
                        .commit()
                }
            }
        }

    }
}