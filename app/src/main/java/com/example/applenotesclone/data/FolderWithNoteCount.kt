package com.example.applenotesclone.data

import androidx.room.Embedded

data class FolderWithNoteCount(
    @Embedded val folder: Folder,
    val noteCount: Int
)
