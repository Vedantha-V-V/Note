package com.example.note

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Note(
    val title:String,
    val content: String,
    val createdAt: Date,
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
)
