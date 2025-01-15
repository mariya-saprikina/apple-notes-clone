package com.example.applenotesclone.data

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class FolderDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: FolderDatabase
    private lateinit var dao: FolderDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.dao
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertTodo() = runTest {
        val folder = Folder("folder1", 1)
        dao.insertFolder(folder)

        val folderFromDb = folder.id?.let { dao.getFolderById(it) }

        assertThat(folderFromDb).isEqualTo(folder)
    }

    @Test
    fun deleteFolder() = runTest {
        val folder = Folder("folder1", 1)
        dao.insertFolder(folder)
        dao.deleteFolder(folder)

        val folderFromDb = folder.id?.let { dao.getFolderById(it) }

        assertThat(folderFromDb).isNull()
    }

    @Test
    fun deleteNote() = runTest {
        val note = Note("note1", "Content", 1, 1)
        dao.insertNote(note)
        dao.deleteNote(note)

        val noteFromDb = note.id?.let { dao.getNoteById(it) }

        assertThat(noteFromDb).isNull()
    }

    @Test
    fun getFolderById() = runTest {
        val folder = Folder("folder1", 1)
        dao.insertFolder(folder)

        val existingFolder = folder.id?.let { dao.getFolderById(it) }
        val nonExistingFolder = dao.getFolderById(999)

        assertThat(existingFolder).isEqualTo(folder)
        assertThat(nonExistingFolder).isNull()
    }

    @Test
    fun getFolderWithNotes() = runTest {
        val folder = Folder("folder1", 1)
        val note1 = Note("note1", "Content", 1, 1)
        val note2 = Note("note2", "Content", 2, 1)
        dao.insertFolder(folder)
        dao.insertNote(note1)
        dao.insertNote(note2)

        val folderWithNotes = dao.getFolderWithNotes(1).first()


        assertThat(folderWithNotes.notes).isEqualTo(listOf(note1, note2))
        assertThat(folderWithNotes.folder).isEqualTo(folder)
    }

    @Test
    fun getAllFoldersWithNoteCounts() = runTest {
        val folder1 = Folder("folder1", 1)
        val folder2 = Folder("folder2", 2)
        val note1 = Note("note1", "Content", 1, 1)
        val note2 = Note("note2", "Content", 2, 1)
        dao.insertFolder(folder1)
        dao.insertFolder(folder2)
        dao.insertNote(note1)
        dao.insertNote(note2)

        val foldersWithNoteCounts = dao.getAllFoldersWithNoteCount().first()

        assertThat(foldersWithNoteCounts[0].folder).isEqualTo(folder1)
        assertThat(foldersWithNoteCounts[0].noteCount).isEqualTo(2)
        assertThat(foldersWithNoteCounts[1].folder).isEqualTo(folder2)
        assertThat(foldersWithNoteCounts[1].noteCount).isEqualTo(0)
    }
}