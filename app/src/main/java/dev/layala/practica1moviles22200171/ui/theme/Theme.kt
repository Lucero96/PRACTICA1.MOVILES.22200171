package dev.layala.practica1moviles22200171.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = VerdePrincipal,     // Barra superior y botones
    onPrimary = Blanco,           // Texto blanco
    secondary = VerdeSuave,       // Acentos
    onSecondary = Blanco,
    background = VerdeFondo,      // Fondo principal de la app
    onBackground = GrisTexto,
    surface = Blanco,             // Cards y fondos secundarios
    onSurface = GrisTexto
)

private val DarkColors = darkColorScheme(
    primary = VerdeSuave,
    onPrimary = VerdeFondo,
    secondary = VerdeMenta,
    onSecondary = GrisTexto,
    background = VerdePrincipal,
    onBackground = Blanco,
    surface = VerdeSuave,
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
