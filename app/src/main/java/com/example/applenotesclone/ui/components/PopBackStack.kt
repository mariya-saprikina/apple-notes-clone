package com.example.applenotesclone.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.applenotesclone.ui.theme.DarkYellowColor

@Composable
fun PopBackStack(modifier: Modifier = Modifier, text: String) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            Icons.Outlined.ArrowBackIosNew,
            contentDescription = "Go Back",
            modifier = Modifier.size(18.dp),
            tint = DarkYellowColor
        )
        Text(text = text, color = DarkYellowColor, style = MaterialTheme.typography.bodyMedium)
    }
}