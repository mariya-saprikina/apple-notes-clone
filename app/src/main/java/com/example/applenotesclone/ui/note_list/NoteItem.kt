package com.example.applenotesclone.ui.note_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.applenotesclone.data.Note
import com.example.applenotesclone.ui.theme.DarkGreyFontColor
import com.example.applenotesclone.ui.theme.LightGreyFontColor

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(start = 8.dp)
    ) {
        Text(text = note.title, style = MaterialTheme.typography.headlineSmall)

        note.content?.let {
            Text(text = it, style = MaterialTheme.typography.bodyMedium, color = LightGreyFontColor)
        }
    }
}