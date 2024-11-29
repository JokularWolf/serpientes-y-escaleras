package com.example.avance2.composables

import com.example.avance2.R

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000428)) // Fondo principal
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Navbar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.inicio2), // Icono de menú (simulado)
                    contentDescription = "Menú",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Text("Inicio", fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
                Icon(
                    painter = painterResource(id = R.drawable.inicio2), // Icono de perfil (simulado)
                    contentDescription = "Perfil",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tabla de IMC
            Text("24.5 - Saludable", fontSize = 28.sp, color = Color.White, fontWeight = FontWeight.Bold)
            Text("Tu IMC", fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(top = 4.dp))
            Text("24.5", fontSize = 32.sp, color = Color.White, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            // Barra de progreso de IMC
            LinearProgressIndicator(
                progress = 0.5f, // Ejemplo de progreso
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = Color(0xFF3DDC84),
                trackColor = Color.Gray
            )


            Spacer(modifier = Modifier.height(24.dp))

            // Recomendaciones de ejercicio
            Text(
                "Recomendaciones de ejercicio",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Ejercicio 1
            ExerciseRecommendation(
                title = "Yoga matutino",
                description = "Relajación y flexibilidad"
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Ejercicio 2
            ExerciseRecommendation(
                title = "Cardio intenso",
                description = "Quema de grasa y resistencia"
            )
        }
    }
}

@Composable
fun ExerciseRecommendation(title: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1C1C1E))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.inicio2), // Imagen del ejercicio (simulada)
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 14.sp, color = Color.Gray)
        }

        Button(
            onClick = { /* Acción de "Ver más" */ },
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .padding(start = 8.dp)
        ) {
            Text("Ver más", fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
