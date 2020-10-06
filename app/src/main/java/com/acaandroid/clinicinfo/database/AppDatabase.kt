package com.acaandroid.clinicinfo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acaandroid.clinicinfo.Patient

@Database(entities = [Patient::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun patientDao() : PatientDao
}