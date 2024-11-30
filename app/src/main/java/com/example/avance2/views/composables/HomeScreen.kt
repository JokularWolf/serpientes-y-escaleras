package com.example.avance2.views.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avance2.R
import com.example.avance2.enums.EnumHome

@Composable
fun HomeScreen(
    onLocalGameClick: () -> Unit = {},  // Callback para "Partida Local"
    onCreateGameClick: () -> Unit = {}, // Callback para "Crear Partida"
    currentAction: MutableState<EnumHome> // Estado que maneja la acción actual
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.home), // Imagen de fondo
            contentDescription = "Fondo de Serpientes y Escaleras",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Ajusta la imagen al tamaño del contenedor
        )

        // Contenido de la interfaz
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título del juego
            Text(
                text = "Serpientes y Escaleras",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .background(Color(0xAA000000), RoundedCornerShape(12.dp)) // Fondo semitransparente
                    .padding(8.dp) // Espaciado interno para el texto
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para "Partida Local"
            Button(
                onClick = {
                    currentAction.value = EnumHome.JUGAR_PARTIDA
                    onLocalGameClick() // Acción adicional que puedes agregar
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(12.dp)),
            ) {
                Text("Partida Local", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para "Crear Partida"
            Button(
                onClick = {
                    currentAction.value = EnumHome.CREAR_PARTIDA
                    onCreateGameClick() // Acción adicional que puedes agregar
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(12.dp)),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50) // Verde para distinguir
                )
            ) {
                Text("Crear Partida", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para "Salir"
            Button(
                onClick = { currentAction.value = EnumHome.SALIR },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(12.dp)),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = Color.Red // Rojo para Salir
                )
            ) {
                Text("Salir", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSnakeLaddersHomeScreen() {
    val currentAction = remember { mutableStateOf(EnumHome.CREAR_PARTIDA) }
    HomeScreen(currentAction = currentAction)
}
