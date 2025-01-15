package com.example.applenotesclone.ui.add_edit_note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.applenotesclone.R
import com.example.applenotesclone.ui.components.Footer
import com.example.applenotesclone.ui.components.PopBackStack
import com.example.applenotesclone.ui.components.TransparentHintTextField
import com.example.applenotesclone.ui.theme.DarkYellowColor
import com.example.applenotesclone.util.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackBar -> snackBarHostState.showSnackbar(
                    message = event.message,
                    duration = SnackbarDuration.Short
                )
                else -> Unit
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        ) {
            PopBackStack(
                modifier = Modifier
                    .clickable {
                        viewModel.onEvent(AddEditNoteEvent.OnPopBackStackClick)
                    },
                text = viewModel.currentBackStackText
            )
            Text(
                text = "Done",
                color = DarkYellowColor,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable {
                    viewModel.onEvent(AddEditNoteEvent.OnSaveNoteClick)
                }
            )
        }
    }, bottomBar = {
        Footer(contentHorizontalArrangement = Arrangement.SpaceBetween) {
            Icon(
                painter = painterResource(R.drawable.icons8_tod_list_50),
                contentDescription = "Todo list"
            )
            Icon(
                painter = painterResource(R.drawable.icons8_camera_50),
                contentDescription = "Take a photo"
            )
            Icon(
                painter = painterResource(R.drawable.markup_icon_207384),
                contentDescription = "Markup",
            )
            Icon(
                painter = painterResource(R.drawable.icons8_create_48),
                contentDescription = "Create a note"
            )
        }
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(8.dp)
        ) {
            TransparentHintTextField(
                text = viewModel.noteTitle.collectAsState().value,
                hint = "Choose a title",
                onValueChange = { title ->
                    viewModel.onEvent(AddEditNoteEvent.OnTitleChange(title))
                },
                textStyle = MaterialTheme.typography.headlineSmall,
                singleLine = true
            )
//            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = viewModel.noteContent,
                hint = "Enter some content...",
                onValueChange = { content ->
                    viewModel.onEvent(AddEditNoteEvent.OnContentChange(content))
                },
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}