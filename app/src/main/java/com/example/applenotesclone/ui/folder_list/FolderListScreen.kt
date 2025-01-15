package com.example.applenotesclone.ui.folder_list

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.applenotesclone.R
import com.example.applenotesclone.ui.components.Footer
import com.example.applenotesclone.ui.components.Headline
import com.example.applenotesclone.ui.components.NotesCard
import com.example.applenotesclone.ui.components.NotesDivider
import com.example.applenotesclone.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderListScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: FolderListViewModel = hiltViewModel()
) {
    val foldersWithNoteCount = viewModel.foldersWithNoteCount.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> {}
            }
        }
    }

    Scaffold(
        bottomBar = {
            Footer(contentHorizontalArrangement = Arrangement.SpaceBetween) {
                Icon(
                    painter = painterResource(id = R.drawable.icons8_tod_list_50),
                    contentDescription = "Todo List"
                )
                Icon(
                    painter = painterResource(id = R.drawable.icons8_camera_50),
                    contentDescription = "Camera"
                )
                Icon(
                    painter = painterResource(R.drawable.icons8_create_48),
                    contentDescription = "Create a note",
                    modifier = Modifier.clickable {
                        viewModel.onEvent(FolderListEvent.OnAddNoteClick)
                    }
                )
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Headline(text = "Folders")
            NotesCard {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    itemsIndexed(foldersWithNoteCount.value) {index, folder ->
                        FolderItem(folderWithNoteCount = folder,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable {
                                    viewModel.onEvent(FolderListEvent.OnFolderClick(folder.folder.id))
                                })

                        if (index < foldersWithNoteCount.value.size - 1) {
                            NotesDivider(paddingStart = 50.dp)
                        }
                    }
                }
            }
        }
    }
}

