package com.example.applenotesclone.data

import androidx.room.Query
import com.example.applenotesclone.data.relations.FolderWithNotes
import kotlinx.coroutines.flow.Flow

interface FolderRepository {

    suspend fun insertFolder(folder: Folder)

    suspend fun insertNote(note: Note)

    suspend fun getNoteById(id: Int): Note?

    suspend fun getFolderById(id: Int): Folder?

    suspend fun getFolderByName(name: String): Folder?

    fun getFolderWithNotes(folderId: Int): Flow<FolderWithNotes>

    fun getAllFoldersWithNoteCount(): Flow<List<FolderWithNoteCount>>
}