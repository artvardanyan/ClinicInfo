package com.acaandroid.clinicinfo.data_base

import androidx.room.PrimaryKey

data class Patient(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val city: String,
    val phone: String,

)