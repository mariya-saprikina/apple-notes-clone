package com.example.applenotesclone.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title: String,
    val content: String?,
    @PrimaryKey val id: Int? = null,
    val folderId: Int
)
