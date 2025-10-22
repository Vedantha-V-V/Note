package com.example.note

import java.util.Date

sealed interface NoteEvent {
    object UpdateNote: NoteEvent
    data class SetTitle(val title: String): NoteEvent
    data class SetContent(val content: String): NoteEvent
    object ShowNote: NoteEvent
    object HideNote: NoteEvent
    data class DeleteNote(val note:Note): NoteEvent
    data class AddNote(val note:Note):NoteEvent
}