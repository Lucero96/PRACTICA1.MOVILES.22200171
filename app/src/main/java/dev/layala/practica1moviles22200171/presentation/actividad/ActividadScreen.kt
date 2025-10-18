package dev.layala.practica1moviles22200171.presentation.actividad

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    // 🔹 Variables de estado
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
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "🏃 Registro de Actividad Física",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.MENU) }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver al menú")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🔹 Dropdown funcional (ExposedDropdownMenuBox)
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = tipoActividad,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Tipo de actividad") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    actividades.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                tipoActividad = opcion
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // 🔹 Campo de duración
            OutlinedTextField(
                value = duracion,
                onValueChange = { duracion = it },
                label = { Text("Duración (en minutos)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            // 🔹 RadioButtons para intensidad
            Text("Intensidad:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))

            intensidades.forEach { opcion ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = intensidad == opcion,
                        onClick = { intensidad = opcion }
                    )
                    Text(opcion, fontSize = 16.sp)
                }
            }

            Spacer(Modifier.height(24.dp))

            // 🔹 Botón Calcular
            Button(
                onClick = {
                    val duracionNum = duracion.toIntOrNull()

                    // Validaciones
                    if (tipoActividad.isBlank() || duracionNum == null || duracionNum <= 0 || intensidad.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("⚠️ Verifica los datos ingresados")
                        }
                        resultado = ""
                    } else {
                        // Calorías por minuto según tipo
                        val caloriasPorMin = when (tipoActividad) {
                            "Correr" -> 10
                            "Caminar" -> 5
                            "Nadar" -> 8
                            "Ciclismo" -> 7
                            "Yoga" -> 4
                            else -> 0
                        }

                        // Factor por intensidad
                        val factor = when (intensidad) {
                            "Baja" -> 0.8
                            "Media" -> 1.0
                            "Alta" -> 1.2
                            else -> 1.0
                        }

                        val totalCalorias = caloriasPorMin * duracionNum * factor

                        resultado =
                            "🔥 En tu sesión de $tipoActividad ($intensidad) de $duracionNum minutos, " +
                                    "quemaste aproximadamente ${"%.2f".format(totalCalorias)} calorías."
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                )
            ) {
                Text("Calcular", fontSize = 18.sp)
            }

            Spacer(Modifier.height(24.dp))

            // 🔹 Resultado
            if (resultado.isNotBlank()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = resultado,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Spacer(Modifier.height(16.dp))

            OutlinedButton(
                onClick = { navController.navigate(Routes.MENU) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("⬅️ Volver al Menú", fontSize = 16.sp)
            }
        }
    }
}
