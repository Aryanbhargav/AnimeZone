package com.manga.mangazone.presentation.components



import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AnimeHeading(text: String,fontSize: Int =32) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFF8747EE), Color(0xFFC9B1F1))
    )
    Text(
        modifier = Modifier.padding(16.dp),
        text = text,
        textAlign = TextAlign.Start,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            brush = gradientBrush,
            shadow = Shadow(
                color = Color(0xFFF6ECAB), offset = Offset(2f, 2f), blurRadius = 4f
            )
        )
    )
}