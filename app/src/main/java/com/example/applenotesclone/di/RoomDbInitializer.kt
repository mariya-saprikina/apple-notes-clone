package com.example.applenotesclone.di

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.applenotesclone.data.Folder
import com.example.applenotesclone.data.FolderDao
import com.example.applenotesclone.data.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class RoomDbInitializer(
    private val folderProvider: Provider<FolderDao>,
) : RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
        populateFolders()
    }

    private suspend fun populateFolders() {
        val folderId1 = 1
        val folderName1 = "Notes"
        folderProvider.get().insertFolder(Folder(folderName1, folderId1))
        folderProvider.get().insertNote(Note("Note1", "Content1", folderId = folderId1))
        folderProvider.get().insertNote(Note("Note2", "Content2", folderId = folderId1))
        folderProvider.get().insertNote(Note("Note3", "Content3", folderId = folderId1))

        val folderId2 = 2
        val folderName2 = "Documents"
        folderProvider.get().insertFolder(Folder(folderName2, folderId2))
        folderProvider.get().insertNote(Note("Note1", "Content1", folderId = folderId2))
        folderProvider.get().insertNote(Note("Note2", "Content2", folderId = folderId2))
        folderProvider.get().insertNote(Note("Note3", "Content3", folderId = folderId2))
        folderProvider.get().insertNote(Note("Note4", "Content4", folderId = folderId2))
    }
}

///**
// * This is a [Sequence] generator to generate random folders.
// */
//val folderGenerator = generateSequence {
//    Folder(
//        id = Random.nextInt(1, 50),
//        name = "folder_${UUID.randomUUID().toString().replace("-", "").substring(0, 6)}"
//    )
//}