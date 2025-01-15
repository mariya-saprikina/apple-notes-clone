package com.example.applenotesclone.ui.add_edit_note

import com.example.applenotesclone.ui.note_list.NoteListEvent

sealed class AddEditNoteEvent {
    data class OnTitleChange(val value: String): AddEditNoteEvent()
    data class OnContentChange(val value: String): AddEditNoteEvent()
    object OnSaveNoteClick: AddEditNoteEvent()
    object OnPopBackStackClick: AddEditNoteEvent()
}
