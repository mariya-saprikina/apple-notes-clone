package com.example.applenotesclone.ui.add_edit_note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applenotesclone.data.FolderRepository
import com.example.applenotesclone.data.Note
import com.example.applenotesclone.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val repository: FolderRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    var noteTitle by mutableStateOf("")
//        private set
    private val _noteTitle = MutableStateFlow("")
    val noteTitle = _noteTitle.asStateFlow()

    var noteContent by mutableStateOf("")
        private set

    var currentBackStackText by mutableStateOf("Folders")
        private set


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var currentNoteId: Int? = null
    private var currentFolderId: Int = 1

    init {
        savedStateHandle.get<Int>("folderId")?.let { folderId ->
            if (folderId != -1) {
                viewModelScope.launch {
                    updateFolderInfo(folderId)
                }
            }
        }
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    repository.getNoteById(noteId)?.let { note ->
                        currentNoteId = note.id
                        _noteTitle.value = note.title
                        noteContent = note.content ?: ""
                        updateFolderInfo(note.folderId)
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.OnTitleChange -> {
                _noteTitle.value = event.value
            }

            is AddEditNoteEvent.OnContentChange -> {
                noteContent = event.value
            }

            AddEditNoteEvent.OnSaveNoteClick -> {
                viewModelScope.launch {
                    if (_noteTitle.value.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackBar(
                                message = "The title can't be empty"
                            )
                        )
                        return@launch
                    }
                    repository.insertNote(
                        Note(
                            title = _noteTitle.value,
                            content = noteContent,
                            id = currentNoteId,
                            folderId = currentFolderId
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }

            AddEditNoteEvent.OnPopBackStackClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private suspend fun updateFolderInfo(folderId: Int) {
        repository.getFolderById(folderId)?.let { folder ->
            currentFolderId = folderId
            currentBackStackText = folder.name
        }
    }
}