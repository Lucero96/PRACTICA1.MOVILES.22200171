package dev.layala.practica1moviles22200171.presentation.actividad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.layala.practica1moviles22200171.presentation.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActividadScreen(navController: NavController) {
    var tipo by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }
    var intensidad by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val gradient = Brush.horizontalGradient(
        listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.secondary)
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("🏃 Actividad Física", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.MENU) }) {
                        Icon(Icons.Filled.ArrowBackIosNew, contentDescription = "Volver", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primary)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Filled.FitnessCenter, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(64.dp))
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = tipo, onValueChange = { tipo = it }, label = { Text("Tipo de actividad") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = duracion, onValueChange = { duracion = it }, label = { Text("Duración (min)") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))

            Text("Intensidad:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            val opciones = listOf("Baja", "Media", "Alta")
            opciones.forEach {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    RadioButton(selected = intensidad == it, onClick = { intensidad = it })
                    Text(it)
                }
            }

            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    val minutos = duracion.toIntOrNull()
                    if (tipo.isBlank() || minutos == null || minutos <= 0 || intensidad.isBlank()) {
                        scope.launch { snackbarHostState.showSnackbar("⚠️ Verifica los datos ingresados") }
                        resultado = ""
                    } else {
                        val calPorMin = when (tipo) {
                            "Correr" -> 10; "Caminar" -> 5; "Nadar" -> 8; "Ciclismo" -> 7; "Yoga" -> 4; else -> 5
                        }
                        val factor = when (intensidad) { "Baja" -> 0.8; "Media" -> 1.0; "Alta" -> 1.2; else -> 1.0 }
                        val total = calPorMin * minutos * factor
                        resultado = "🔥 En tu sesión de $tipo ($intensidad), quemaste ${"%.2f".format(total)} calorías."
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp).background(gradient, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color.Transparent)
            ) { Text("Calcular", color = MaterialTheme.colorScheme.onPrimary, fontSize = 18.sp) }

            Spacer(Modifier.height(24.dp))
            if (resultado.isNotBlank()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(resultado, modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center, fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface)
                }
            }

            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate(Routes.MENU) },
                modifier = Modifier.fillMaxWidth().height(50.dp).background(gradient, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color.Transparent)
            ) { Text("⬅️ Volver al Menú", color = MaterialTheme.colorScheme.onPrimary, fontSize = 18.sp, fontWeight = FontWeight.Medium) }
        }
    }
}
