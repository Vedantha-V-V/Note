package com.example.note

import android.app.Application
import androidx.room.Room
import com.example.note.db.NotesDB

class MainApplication : Application(){
    companion object{
        lateinit var notesDB: NotesDB
    }

    override fun onCreate(){
        super.onCreate()
        notesDB = Room.databaseBuilder(
            applicationContext,
            NotesDB::class.java,
            NotesDB.NAME
        ).build()
    }
}