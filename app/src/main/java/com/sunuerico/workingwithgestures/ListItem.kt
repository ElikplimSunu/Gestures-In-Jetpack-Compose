package com.sunuerico.workingwithgestures

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ListItem(){
    Card {
        Text(text = "Homepage",
            style = TextStyle(fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black).copy(lineHeight = 32.sp))
    }
}