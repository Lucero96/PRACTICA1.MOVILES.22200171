package dev.layala.practica1moviles22200171.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = AzulSuave,
    onPrimary = Blanco,
    secondary = AmarilloSuave,
    onSecondary = AzulOscuro,
    background = AzulOscuro,
    onBackground = GrisTexto,
    surface = AzulMedio,
    onSurface = GrisTexto
)

@Composable
fun PRACTICA1MOVILES22200171Theme(
    darkTheme: Boolean = true, // 🌙 Siempre modo oscuro para este diseño
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = Typography,
        content = content
    )
}
