package dev.layala.practica1moviles22200171.presentation.actividad

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.layala.practica1moviles22200171.presentation.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActividadScreen(navController: NavController) {

    var tipoActividad by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var duracion by remember { mutableStateOf("") }
    var intensidad by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    val actividades = listOf("Correr", "Caminar", "Nadar", "Ciclismo", "Yoga")
    val intensidades = listOf("Baja", "Media", "Alta")
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Registro de Actividad Física", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.MENU) }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                OutlinedTextField(
                    value = tipoActividad,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de actividad") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    actividades.forEach { opcion ->
                        DropdownMenuItem(text = { Text(opcion) }, onClick = { tipoActividad = opcion; expanded = false })
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            OutlinedTextField(value = duracion, onValueChange = { duracion = it }, label = { Text("Duración (minutos)") }, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(16.dp))
            Text("Intensidad:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            intensidades.forEach { opcion ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    RadioButton(selected = intensidad == opcion, onClick = { intensidad = opcion })
                    Text(opcion)
                }
            }

            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    val duracionNum = duracion.toIntOrNull()
                    if (tipoActividad.isBlank() || duracionNum == null || duracionNum <= 0 || intensidad.isBlank()) {
                        scope.launch { snackbarHostState.showSnackbar("⚠️ Verifica los datos ingresados") }
                        resultado = ""
                    } else {
                        val caloriasPorMin = when (tipoActividad) {
                            "Correr" -> 10; "Caminar" -> 5; "Nadar" -> 8; "Ciclismo" -> 7; "Yoga" -> 4; else -> 0
                        }
                        val factor = when (intensidad) { "Baja" -> 0.8; "Media" -> 1.0; "Alta" -> 1.2; else -> 1.0 }
                        val totalCalorias = caloriasPorMin * duracionNum * factor
                        resultado = "🔥 En tu sesión de $tipoActividad ($intensidad) de $duracionNum minutos, quemaste ${"%.2f".format(totalCalorias)} calorías."
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)
            ) { Text("Calcular", fontSize = 18.sp, fontWeight = FontWeight.Medium) }

            Spacer(Modifier.height(24.dp))
            if (resultado.isNotBlank()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) { Text(resultado, modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center, fontSize = 18.sp, color = MaterialTheme.colorScheme.onSurface) }
            }

            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate(Routes.MENU) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)
            ) { Text("⬅️ Volver al Menú", fontSize = 18.sp, fontWeight = FontWeight.Medium) }
        }
    }
}
