package com.example.note.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.note.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): LiveData<List<Note>>
    @Insert
    suspend fun addNote(note: Note)
//    @Upsert
//    suspend fun updateNote(note: Note)
    @Query("Delete FROM note where id = :id")
    suspend fun deleteNote(id: Int)

//    @Query("SELECT * FROM note WHERE id = :noteId")
//    fun getNote(noteId: Int): Flow<Note>


}