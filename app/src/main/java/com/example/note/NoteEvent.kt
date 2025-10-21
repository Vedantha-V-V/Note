package com.example.note

import java.util.Date

sealed interface NoteEvent {
    object SaveNote: NoteEvent
    data class SetTitle(val title: String): NoteEvent
    data class SetContent(val content: String): NoteEvent
    data class DeleteNote(val note:Note): NoteEvent
    data class UpdateNote(val note:Note):NoteEvent
}