package com.acaandroid.clinicinfo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "patient")
@Parcelize
data class Patient (
    @PrimaryKey
    val firstName: String,
    val lastName: String
) : Parcelable