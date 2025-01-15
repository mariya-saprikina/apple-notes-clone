package com.example.applenotesclone.ui.folder_list

sealed class FolderListEvent {
    data class OnFolderClick(val folderId: Int?): FolderListEvent()
    object OnAddNoteClick: FolderListEvent()
}
