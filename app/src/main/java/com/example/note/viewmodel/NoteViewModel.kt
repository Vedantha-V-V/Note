package com.example.note.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.Note
import com.example.note.NoteDao
import com.example.note.NoteEvent
import com.example.note.NoteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(
    private val dao: NoteDao
): ViewModel(){
    private val _notes = dao.getNotes()
    private val state = MutableStateFlow(NoteState())
//    val state = combine(_state, _notes){ state, notes ->
//        state.copy(
//            notes = notes
//        )
//    }

    fun onEvent(event: NoteEvent){
        when(event){
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }
            }
            is NoteEvent.AddNote -> {
                var title = state.value.title
                var content = state.value.content
                if(title.isBlank()){
                    title = "New Title"
                }
                if(content.isBlank()){
                    content = "..."
                }
                val note = Note(
                    title = title,
                    content = content
                )
                viewModelScope.launch{
                    dao.addNote(note)
                }
            }
            is NoteEvent.SetTitle -> {
                state.update{ it.copy(title = event.title) }
            }
            is NoteEvent.SetContent -> {
                state.update{ it.copy(content = event.content) }
            }
            is NoteEvent.UpdateNote -> TODO()
            NoteEvent.HideNote -> {
                state.update{ it.copy(isNoting = false) }
            }
            NoteEvent.ShowNote -> {
                state.update{ it.copy(isNoting = false) }
            }
        }
    }
}