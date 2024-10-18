package com.example.calendartracker.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6200EE), // purple_500
    secondary = Color(0xFF03DAC5), // teal_200
    tertiary = Color(0xFF3700B3), // purple_700
    background = Color(0xFF121212), // Dark background
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onSurface = Color.White,
    onBackground = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFBB86FC), // purple_200
    secondary = Color(0xFF03DAC5), // teal_200
    tertiary = Color(0xFF6200EE), // purple_500
    background = Color(0xFFFFFFFF), // Light background
    surface = Color(0xFFFFFFFF), // Light surface
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    onBackground = Color.Black
)

@Composable
fun CalendarTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),

    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
