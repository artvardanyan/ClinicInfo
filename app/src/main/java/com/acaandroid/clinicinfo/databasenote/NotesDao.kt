package com.acaandroid.clinicinfo.databasenote

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.acaandroid.clinicinfo.databasenote.Notes

@Dao
interface NotesDao {

    @Insert
    fun insertNote(note: Notes)

    @Delete
    fun deleteNote(note: Notes)

    @Query("SELECT * FROM notes")
    fun getAllNotes() : List<Notes>

    @Query("SELECT * FROM notes WHERE date=:currentDate")
    fun getNotesByDate(currentDate : String) : List<Notes>
}