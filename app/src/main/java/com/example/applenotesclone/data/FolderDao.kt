package com.example.applenotesclone.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.applenotesclone.data.relations.FolderWithNotes
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder: Folder)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteFolder(folder: Folder)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Query("SELECT * FROM folder WHERE id = :id")
    suspend fun getFolderById(id: Int): Folder?

    @Query("SELECT * FROM folder WHERE name = :name")
    suspend fun getFolderByName(name: String): Folder?

    @Query("SELECT * FROM folder WHERE id = :folderId")
    fun getFolderWithNotes(folderId: Int): Flow<FolderWithNotes>

    @Query("""
        SELECT folder.*, IFNULL(COUNT(note.id), 0) as noteCount
        FROM folder
        LEFT JOIN note ON folder.id = note.folderId
        GROUP BY folder.id
    """)
    fun getAllFoldersWithNoteCount(): Flow<List<FolderWithNoteCount>>
}