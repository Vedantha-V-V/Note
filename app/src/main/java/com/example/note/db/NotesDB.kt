package com.example.note.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.note.Converters
import com.example.note.Note

@Database(entities = [Note::class], version = 1)
@TypeConverters(Converters::class)
abstract class NotesDB: RoomDatabase() {
    companion object {
        const val NAME = "Notes_DB"
    }

    abstract fun getNotesDao() : NoteDao
}