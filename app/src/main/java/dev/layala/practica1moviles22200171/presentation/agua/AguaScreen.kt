package dev.layala.practica1moviles22200171.presentation.agua

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.WaterDrop
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
fun AguaScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
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
                title = { Text("💧 Consumo de Agua", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary) },
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
            Icon(Icons.Filled.WaterDrop, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(64.dp))
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(value = peso, onValueChange = { peso = it }, label = { Text("Peso corporal (kg)") }, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(20.dp))
            Text("Género:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            val generos = listOf("Masculino", "Femenino", "Sin especificar")
            generos.forEach {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    RadioButton(selected = genero == it, onClick = { genero = it })
                    Text(it)
                }
            }

            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    val pesoNum = peso.toDoubleOrNull()
                    if (nombre.isBlank() || pesoNum == null || pesoNum < 5 || pesoNum > 200 || genero.isBlank()) {
                        scope.launch { snackbarHostState.showSnackbar("⚠️ Verifica los datos ingresados") }
                        resultado = ""
                    } else {
                        val factor = when (genero) { "Masculino" -> 1.02; "Femenino" -> 1.01; else -> 1.00 }
                        val litros = pesoNum * 0.035 * factor
                        resultado = "$nombre debe beber ${"%.2f".format(litros)} litros de agua 💧"
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp).background(gradient, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color.Transparent)
            ) { Text("Calcular", color = MaterialTheme.colorScheme.onPrimary, fontSize = 18.sp, fontWeight = FontWeight.Medium) }

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
