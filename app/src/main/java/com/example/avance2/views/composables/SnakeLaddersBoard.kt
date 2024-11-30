package com.example.avance2.views.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SnakeLaddersBoard() {
    // Estado para la posición de la ficha
    var piecePosition by remember { mutableStateOf(Pair(0, 0)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFECECEC)), // Fondo gris claro
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Serpientes y Escaleras",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .size(300.dp) // Dimensiones del tablero
                .background(Color(0xFF3E3E42)), // Fondo oscuro para el tablero
            contentAlignment = Alignment.Center
        ) {
            // Dibujar el tablero
            GameBoardGrid(
                rows = 10,
                columns = 10,
                piecePosition = piecePosition,
                onCellClicked = { row, column ->
                    piecePosition = Pair(row, column)
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto de inicio y meta
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Inicio",
                fontSize = 20.sp,
                color = Color.Green,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = "Meta",
                fontSize = 20.sp,
                color = Color.Red,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Composable
fun GameBoardGrid(
    rows: Int,
    columns: Int,
    piecePosition: Pair<Int, Int>,
    onCellClicked: (Int, Int) -> Unit
) {
    // Dimensiones de las casillas
    val cellSize = 30.dp

    Box(modifier = Modifier.size(cellSize * rows)) {
        // Dibujar las celdas del tablero
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures { offset ->
                        // Detectar la celda donde se hizo clic
                        val cellWidth = size.width / columns
                        val cellHeight = size.height / rows

                        val column = (offset.x / cellWidth).toInt()
                        val row = (offset.y / cellHeight).toInt()

                        if (row in 0 until rows && column in 0 until columns) {
                            onCellClicked(row, column)
                        }
                    }
                }
        ) {
            val cellWidth = size.width / columns
            val cellHeight = size.height / rows

            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    val left = j * cellWidth
                    val top = i * cellHeight
                    val right = left + cellWidth
                    val bottom = top + cellHeight

                    drawRect(
                        color = if ((i + j) % 2 == 0) Color.LightGray else Color.White,
                        topLeft = androidx.compose.ui.geometry.Offset(left, top),
                        size = androidx.compose.ui.geometry.Size(cellWidth, cellHeight)
                    )
                }
            }

            // Dibujar la ficha
            val pieceX = piecePosition.second * cellWidth + cellWidth / 2
            val pieceY = piecePosition.first * cellHeight + cellHeight / 2
            drawCircle(
                color = Color.Blue,
                radius = cellWidth / 4,
                center = androidx.compose.ui.geometry.Offset(pieceX, pieceY)
            )
        }

        // Añadir una serpiente como ejemplo
        SerpentPath()
    }
}

@Composable
fun SerpentPath() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val path = Path().apply {
            moveTo(size.width * 0.2f, size.height * 0.2f) // Punto inicial de la serpiente
            cubicTo(
                size.width * 0.3f, size.height * 0.1f, // Primer control
                size.width * 0.7f, size.height * 0.9f, // Segundo control
                size.width * 0.8f, size.height * 0.8f  // Punto final
            )
        }

        drawPath(
            path = path,
            color = Color.Red,
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 4f)
        )
    }
}

// Agregar un Preview para visualizar el tablero
@Preview(showBackground = true)
@Composable
fun SnakeLaddersBoardPreview() {
    SnakeLaddersBoard()
}
