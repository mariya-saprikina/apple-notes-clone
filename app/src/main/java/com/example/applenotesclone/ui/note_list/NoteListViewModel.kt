package com.example.applenotesclone.ui.note_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applenotesclone.data.Folder
import com.example.applenotesclone.data.FolderRepository
import com.example.applenotesclone.data.relations.FolderWithNotes
import com.example.applenotesclone.util.Routes
import com.example.applenotesclone.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: FolderRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val folderId: Int? = savedStateHandle.get<Int>("folderId")

    val folderWithNotes: Flow<FolderWithNotes> = (folderId.takeIf { it != -1 }?.let {
        repository.getFolderWithNotes(it)
    }) ?: flowOf(FolderWithNotes(Folder(""), emptyList()))

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NoteListEvent) {
        when(event) {
            is NoteListEvent.OnPopBackStackClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }

            is NoteListEvent.OnAddNoteClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_NOTE + "?folderId=${event.folderId}"))
            }
            is NoteListEvent.OnNoteClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_NOTE + "?noteId=${event.note.id}"))
            }

            is NoteListEvent.OnDeleteNoteClick -> {
                viewModelScope.launch {
                    repository.deleteNote(event.note)
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}