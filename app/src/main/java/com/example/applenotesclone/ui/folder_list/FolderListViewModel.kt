package com.example.applenotesclone.ui.folder_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applenotesclone.data.FolderRepository
import com.example.applenotesclone.util.Routes
import com.example.applenotesclone.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderListViewModel @Inject constructor(
    private val repository: FolderRepository
) : ViewModel() {

    val foldersWithNoteCount = repository.getAllFoldersWithNoteCount()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: FolderListEvent) {
        when(event) {
            is FolderListEvent.OnFolderClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.NOTE_LIST + "?folderId=${event.folderId}"))
            }
            is FolderListEvent.OnAddNoteClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_NOTE))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}