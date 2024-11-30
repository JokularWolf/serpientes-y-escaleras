// LoginScreen.kt
package com.example.avance2.views.composables

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.avance2.R
import com.google.firebase.auth.FirebaseAuth


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},  // Callback para redirigir después del inicio exitoso
    onRegisterClick: () -> Unit = {}  // Callback para la acción de "Registrarse"
) {
    val context = LocalContext.current  // Contexto para mostrar mensajes

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000428))
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Iniciar sesión", fontSize = 32.sp, color = Color.White)

            Spacer(modifier = Modifier.height(32.dp))

            var username by remember { mutableStateOf("") }
            BasicTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    if (username.isEmpty()) Text("Correo electrónico", color = Color.Gray)
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            var password by remember { mutableStateOf("") }
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
                visualTransformation = PasswordVisualTransformation(),
                decorationBox = { innerTextField ->
                    if (password.isEmpty()) Text("Contraseña", color = Color.Gray)
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        FirebaseAuth.getInstance()
                            .signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Mostrar un mensaje de éxito
                                    Toast.makeText(
                                        context,
                                        "Inicio de sesión exitoso",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    onLoginSuccess()  // Redirigir tras inicio exitoso
                                } else {
                                    // Mostrar el error de Firebase
                                    val error = task.exception?.localizedMessage ?: "Error desconocido"
                                    Toast.makeText(
                                        context,
                                        "Error al iniciar sesión: $error",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    } else {
                        Toast.makeText(context, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Ingresar", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto de "Registrarse" con acción de onClick
            Text(
                text = "Registrarse",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.clickable { onRegisterClick() }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Opción de "¿Olvidó su contraseña?"
            Text("¿Olvidó su contraseña?", color = Color.Gray, fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen()
}