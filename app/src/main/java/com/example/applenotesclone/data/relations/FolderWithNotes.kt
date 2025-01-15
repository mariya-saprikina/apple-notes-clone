package com.example.applenotesclone.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.applenotesclone.data.Folder
import com.example.applenotesclone.data.Note

data class FolderWithNotes(
    @Embedded val folder: Folder,
    @Relation(
        parentColumn = "id",
        entityColumn = "folderId"
    )
    val notes: List<Note>
)