package com.example.applenotesclone.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.applenotesclone.ui.theme.FooterBackground
import com.example.applenotesclone.ui.theme.GreyDividerColor
import com.example.applenotesclone.ui.theme.DarkYellowColor

@Composable
fun Footer(
    contentHorizontalArrangement: Arrangement.Horizontal = Arrangement.End,
    content: @Composable () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(50.dp)) {
        Divider(
            color = GreyDividerColor,
            thickness = 0.5.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
        BottomAppBar(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            contentColor = DarkYellowColor,
            contentPadding = PaddingValues(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = contentHorizontalArrangement,
                verticalAlignment = Alignment.CenterVertically
            ) {
                content()
            }
        }
    }
}