package com.example.applenotesclone.ui.note_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.applenotesclone.R
import com.example.applenotesclone.data.Folder
import com.example.applenotesclone.data.relations.FolderWithNotes
import com.example.applenotesclone.ui.components.Footer
import com.example.applenotesclone.ui.components.PopBackStack
import com.example.applenotesclone.ui.components.Headline
import com.example.applenotesclone.ui.components.NotesCard
import com.example.applenotesclone.ui.components.NotesDivider
import com.example.applenotesclone.ui.components.SwipeToDeleteContainer
import com.example.applenotesclone.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit,
    onNavigate: (UiEvent.Navigate) -> Unit
) {

    val folderWithNotes = viewModel.folderWithNotes.collectAsState(
        initial = FolderWithNotes(Folder(""),
        emptyList()))

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            PopBackStack(
                modifier = Modifier
                    .padding(6.dp)
                    .clickable { viewModel.onEvent(NoteListEvent.OnPopBackStackClick) },
                text = "Folders"
            )
        },
        bottomBar = {
            Footer(contentHorizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "${folderWithNotes.value.notes.size} Notes")
                Icon(
                    painter = painterResource(R.drawable.icons8_create_48),
                    contentDescription = "Create a note",
                    modifier = Modifier.clickable {
                        viewModel.onEvent(NoteListEvent.OnAddNoteClick(folderWithNotes.value.folder.id))
                    }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Headline(text = folderWithNotes.value.folder.name)
            NotesCard {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(folderWithNotes.value.notes, key = { _, note -> note.id as Any }) { index, note ->
                        SwipeToDeleteContainer(
                            item = note,
                            onDelete = {
                                viewModel.onEvent(NoteListEvent.OnDeleteNoteClick(it))
                            }
                        ) { note ->
                            NoteItem(
                                note = note,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .clickable {
                                        viewModel.onEvent(NoteListEvent.OnNoteClick(note))
                                    }
                            )
                        }

                        if (index < folderWithNotes.value.notes.size - 1) {
                            NotesDivider(paddingStart = 20.dp)
                        }
                    }
                }
            }
        }
    }
}