package com.example.applenotesclone.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Headline(text: String) {
    Text(modifier = Modifier.padding(5.dp), text = text, style = MaterialTheme.typography.headlineLarge)
}