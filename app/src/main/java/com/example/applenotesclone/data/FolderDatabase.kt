package com.example.applenotesclone.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Folder::class, Note::class], version = 1)
abstract class FolderDatabase: RoomDatabase() {

    abstract val dao: FolderDao
}