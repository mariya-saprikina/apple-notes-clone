package com.example.applenotesclone.ui.note_list

import com.example.applenotesclone.data.Note

sealed class NoteListEvent{
    object OnPopBackStackClick: NoteListEvent()
    data class OnNoteClick(val note: Note): NoteListEvent()
    data class OnAddNoteClick(val folderId: Int?): NoteListEvent()

    data class OnDeleteNoteClick(val note: Note) : NoteListEvent()
}
