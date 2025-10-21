package com.example.note.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.NoteDao
import com.example.note.NoteEvent
import com.example.note.NoteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dao: NoteDao
): ViewModel(){
    private val _notes = dao.getNotes()
    private val _state = MutableStateFlow(NoteState())

    fun onEvent(event: NoteEvent){
        when(event){
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }
            is NoteEvent.SaveNote -> {

            }
            is NoteEvent.SetTitle -> {
                _state.update{ it.copy(title = event.title) }
            }
            is NoteEvent.SetContent -> {
                _state.update{ it.copy(content = event.content) }
            }
            is NoteEvent.UpdateNote -> TODO()
        }
    }
}