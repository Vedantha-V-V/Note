package com.example.note

import java.util.Date

data class NoteState(
    val notes: List<Note> = emptyList(),
    val title: String = "",
    val content: String = ""
)
