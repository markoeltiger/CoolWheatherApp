package com.example.coolwheatherapp.widgets

import android.content.Context
import android.graphics.Color
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.unit.ColorProvider
import com.example.coolwheatherapp.R

class WeatherWidget {
    @Composable
    fun WaterWidgetCounter(
        context: Context,
        glassesOfWater: Int,
        modifier: GlanceModifier
    ) {
        Text(
            text = "حالة الجو"
            ,
            modifier = Modifier.alpha(1f),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = White
                )
            )

    }
}