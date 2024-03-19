package com.example.calculatorapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = LightWhiteButton,
    secondary = LightGrayButton,
    tertiary = LightGreenButton,
    background = LightBackGround ,
    surface = LightRedOnButton,
    onPrimary = LightGreenOnButton,
    onSecondary = LightNumberColor,
    onTertiary = LightBackspaceColor,
    onBackground = LightOperationColor,
    onSurface = LightDisabledBackspaceColor,
    surfaceTint = LightResultColor,
    scrim = LightDividerColor
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlackButton,
    secondary = DarkGrayButton,
    tertiary = DarkGreenButton,
    background = DarkBackGround ,
    surface = DarkRedOnButton ,
    onPrimary = DarkGreenOnButton,
    onSecondary = DarkNumberColor,
    onTertiary = DarkBackspaceColor,
    onBackground = DarkOperationColor,
    onSurface = DarkDisabledBackspaceColor,
    surfaceTint = DarkResultColor,
    scrim = DarkDividerColor


)

@Composable
fun CalculatorAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}