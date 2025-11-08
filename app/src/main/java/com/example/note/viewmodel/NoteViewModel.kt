package com.example.note.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.MainApplication
import com.example.note.Note
import com.example.note.db.NoteDao
import com.example.note.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date
import kotlin.collections.emptyList

class NoteViewModel: ViewModel(){
    val notesDao = MainApplication.notesDB.getNotesDao()
    val notes : LiveData<List<Note>> = notesDao.getAllNotes()

    val isNoting = mutableStateOf(false)

    @RequiresApi(Build.VERSION_CODES.O)
    fun addNote(title:String, content:String){
        viewModelScope.launch(Dispatchers.IO){
            notesDao.addNote(Note(title=title, content=content,createdAt= Date.from(Instant.now())))
        }
    }

    fun deleteNote(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            notesDao.deleteNote(id)
        }

    }
}