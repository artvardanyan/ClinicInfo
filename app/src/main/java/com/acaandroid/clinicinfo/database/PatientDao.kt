package com.acaandroid.clinicinfo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.acaandroid.clinicinfo.Patient

@Dao
interface PatientDao {

    @Query("SELECT * FROM patient")
    suspend fun getAll():List<Patient>

    @Query("SELECT * FROM patient where lastName= :lastName and firstName= :firstName")
    suspend fun getUser(lastName: String?, firstName: String?): Patient?

    @Insert
    suspend fun insertAll(patient: Patient)

    @Delete
    suspend fun delete(patient: Patient)
}