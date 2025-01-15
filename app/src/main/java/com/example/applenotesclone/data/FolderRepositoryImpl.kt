package com.example.applenotesclone.data

import com.example.applenotesclone.data.relations.FolderWithNotes
import kotlinx.coroutines.flow.Flow

class FolderRepositoryImpl(
    private val dao: FolderDao
): FolderRepository {
    override suspend fun insertFolder(folder: Folder) {
        dao.insertFolder(folder)
    }

    override suspend fun insertNote(note: Note) {
       dao.insertNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun getFolderById(id: Int): Folder? {
        return dao.getFolderById(id)
    }

    override suspend fun getFolderByName(name: String): Folder? {
        return dao.getFolderByName(name)
    }

    override fun getFolderWithNotes(folderId: Int): Flow<FolderWithNotes> {
        return dao.getFolderWithNotes(folderId)
    }

    override fun getAllFoldersWithNoteCount(): Flow<List<FolderWithNoteCount>> {
        return dao.getAllFoldersWithNoteCount()
    }
}