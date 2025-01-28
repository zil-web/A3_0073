package com.example.uaspam.ui.Customwidget

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier, // Menerima modifier dari luar
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFDAB6FC),
            contentColor = Color(0xFF6A1B9A)
        ),
        shape = RoundedCornerShape(50.dp), // Bentuk oval
        modifier = modifier // Memastikan tombol menggunakan modifier dari parameter
            .padding(8.dp)
            .height(40.dp) // Ketinggian tetap untuk visual yang konsisten
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(0xFF6A1B9A)
        )
    }
}
