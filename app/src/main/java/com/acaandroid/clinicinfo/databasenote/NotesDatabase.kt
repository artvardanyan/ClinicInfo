package com.acaandroid.clinicinfo.databasenote

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acaandroid.clinicinfo.databasenote.NotesDao

@Database(entities = [Notes::class], version = 1)
abstract class ClinicInfo : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}