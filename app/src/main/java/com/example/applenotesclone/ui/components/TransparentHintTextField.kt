package com.example.applenotesclone.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.applenotesclone.ui.theme.DarkGreyFontColor
import com.example.applenotesclone.ui.theme.LightGreyFontColor
import com.example.applenotesclone.ui.theme.DarkYellowColor
import com.example.applenotesclone.ui.theme.LightYellowColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false
) {
    Box(
        modifier = modifier
    ) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text(text = hint, style = textStyle, color = LightGreyFontColor) },
            colors = TextFieldDefaults.textFieldColors(
                textColor = DarkGreyFontColor,
                containerColor = Color.Transparent,
                cursorColor = DarkYellowColor,
                selectionColors = TextSelectionColors(DarkYellowColor, LightYellowColor),
                unfocusedIndicatorColor = Color.Transparent, // Hide the border when TextField is unfocused
                focusedIndicatorColor = Color.Transparent // Hide the border when TextField is focused,
            )
        )
    }
}