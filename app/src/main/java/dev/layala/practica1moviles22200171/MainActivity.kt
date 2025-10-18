package dev.layala.practica1moviles22200171

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.layala.practica1moviles22200171.presentation.navigation.AppNavGraph
import dev.layala.practica1moviles22200171.ui.theme.PRACTICA1MOVILES22200171Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PRACTICA1MOVILES22200171Theme {
                AppNavGraph()
            }
        }
    }
}
