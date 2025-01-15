package com.example.applenotesclone.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Folder(
    val name: String,
    @PrimaryKey val id: Int? = null
)
