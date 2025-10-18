package dev.layala.practica1moviles22200171.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = VerdeBosque,          // Botones principales, topbar
    onPrimary = Blanco,             // Texto en botones verdes
    secondary = VerdeHoja,          // Textos destacados
    onSecondary = Blanco,
    background = VerdeMenta,        // Fondo principal
    onBackground = VerdeBosque,
    surface = CelesteHielo,         // Cards y superficies
    onSurface = VerdeBosque
)

private val DarkColors = darkColorScheme(
    primary = VerdeAgua,
    onPrimary = VerdeBosque,
    secondary = VerdeMenta,
    onSecondary = VerdeBosque,
    background = VerdeBosque,
    onBackground = Blanco,
    surface = VerdeHoja,
    onSurface = Blanco
)

@Composable
fun PRACTICA1MOVILES22200171Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
