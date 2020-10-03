package com.acaandroid.clinicinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this,"I added androidx.core:core-ktx:1.3.2 dependency",Toast.LENGTH_SHORT).show()
        Toast.makeText(this,"I added androidx.core:core-ktx:1.3.2 dependency",Toast.LENGTH_SHORT).show()
    }
}