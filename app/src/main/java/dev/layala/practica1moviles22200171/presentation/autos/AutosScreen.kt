package dev.layala.practica1moviles22200171.presentation.autos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.layala.practica1moviles22200171.data.model.Auto
import dev.layala.practica1moviles22200171.presentation.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutosScreen(navController: NavController) {

    // 🔹 Lista de autos
    val autos = remember {
        listOf(
            Auto(
                "Ferrari",
                "488 GTB",
                350000.0,
                "https://hips.hearstapps.com/es.h-cdn.co/cades/contenidos/14167/ferrari488gtb6.jpg"
            ),
            Auto(
                "Lamborghini",
                "Huracán EVO",
                320000.0,
                "https://hips.hearstapps.com/hmg-prod/images/lamborghini-huracan-super-trofeo-evo2-ok-1622038367.jpg"
            ),
            Auto(
                "Porsche",
                "911 Turbo S",
                270000.0,
                "https://acnews.blob.core.windows.net/imgnews/medium/NAZ_b45d9fcb385a4b85bd9b3bb9c47cda20.webp"
            ),
            Auto(
                "McLaren",
                "720S",
                310000.0,
                "https://mclaren.scene7.com/is/image/mclaren/720S-Coupe_hero:crop-16x9?wid=1920&hei=1080"
            ),
            Auto(
                "Aston Martin",
                "Vantage",
                280000.0,
                "https://s.yimg.com/ny/api/res/1.2/.UmWKbEhEArGwlPPJhnhqw--/YXBwaWQ9aGlnaGxhbmRlcjt3PTEyNDI7aD02OTk-/https://media.zenfs.com/en/the_drive_634/630e263da89a7c8afc52dec43892c5e6"
            )
        )
    }


    val total = autos.sumOf { it.precio }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "🚗 Catálogo de Autos Deportivos",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Routes.MENU) }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver al menú"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            // 🔹 Lista con LazyColumn
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(autos) { auto ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(6.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            AsyncImage(
                                model = auto.imagenUrl,
                                contentDescription = auto.modelo,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = "${auto.marca} ${auto.modelo}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                            Text(
                                text = "Precio: $${"%,.2f".format(auto.precio)}",
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // 🔹 Total
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = "💰 Total: $${"%,.2f".format(total)}",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Spacer(Modifier.height(16.dp))

            // 🔹 Botón Volver
            OutlinedButton(
                onClick = { navController.navigate(Routes.MENU) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("⬅️ Volver al Menú", fontSize = 16.sp)
            }
        }
    }
}
