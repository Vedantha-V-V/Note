package com.example.note

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Upsert
    suspend fun addNote(note: Note)
    @Delete
    suspend fun deleteNote(note:Note)

    @Query("SELECT * FROM note WHERE id = :noteId")
    fun getNote(noteId: Int): Flow<Note>

    @Query("SELECT * FROM note")
    fun getNotes():List<Note>
}