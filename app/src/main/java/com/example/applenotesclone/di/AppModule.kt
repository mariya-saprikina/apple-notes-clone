package com.example.applenotesclone.di

import android.app.Application
import androidx.room.Room
import com.example.applenotesclone.data.FolderDao
import com.example.applenotesclone.data.FolderDatabase
import com.example.applenotesclone.data.FolderRepository
import com.example.applenotesclone.data.FolderRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFolderDatabase(app: Application, folderProvider: Provider<FolderDao>): FolderDatabase {
        return Room.databaseBuilder(
            app,
            FolderDatabase::class.java,
            "app_db"
        )
            .addCallback(
                RoomDbInitializer(folderProvider = folderProvider)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideFolderRepository(db: FolderDatabase): FolderRepository {
        return FolderRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideFolderDao(db: FolderDatabase): FolderDao = db.dao

}