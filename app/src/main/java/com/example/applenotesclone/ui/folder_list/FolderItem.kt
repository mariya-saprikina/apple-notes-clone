package com.example.applenotesclone.ui.folder_list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.applenotesclone.R
import com.example.applenotesclone.data.FolderWithNoteCount
import com.example.applenotesclone.ui.theme.LightGreyFontColor
import com.example.applenotesclone.ui.theme.LightGreyIconColor
import com.example.applenotesclone.ui.theme.DarkYellowColor

@Composable
fun FolderItem(
    folderWithNoteCount: FolderWithNoteCount,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(6.dp),
            painter = painterResource(id = R.drawable.icons8_folder_50),
            contentDescription = "Folder",
            tint = DarkYellowColor
        )
        Spacer(modifier = Modifier.weight(0.05f))
        Text(text = folderWithNoteCount.folder.name, style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = folderWithNoteCount.noteCount.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = LightGreyFontColor
        )
        Icon(
            modifier = Modifier
                .padding(5.dp)
                .size(10.dp),
            imageVector = Icons.Outlined.ArrowForwardIos,
            contentDescription = "Arrow Right",
            tint = LightGreyIconColor
        )
    }
}