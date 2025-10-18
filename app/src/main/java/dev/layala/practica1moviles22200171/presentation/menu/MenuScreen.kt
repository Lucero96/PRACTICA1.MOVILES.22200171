package dev.layala.practica1moviles22200171.presentation.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.layala.practica1moviles22200171.presentation.navigation.Routes

@Composable
fun MenuScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🌿 Bienestar App",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = { navController.navigate(Routes.AGUA) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("💧 Calculadora de consumo de agua")
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Routes.ACTIVIDAD) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("🏃 Registro de actividad física")
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate(Routes.AUTOS) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("🚗 Catálogo de autos deportivos")
            }
        }
    }
}
