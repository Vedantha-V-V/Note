package com.example.note

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Note(
    val title:String,
    val content: String,
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null
)
