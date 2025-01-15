package com.example.applenotesclone.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.applenotesclone.ui.theme.GreyDividerColor

@Composable
fun NotesDivider(paddingStart: Dp) {
    Divider(
        color = GreyDividerColor,
        thickness = 0.5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = paddingStart)
    )
}