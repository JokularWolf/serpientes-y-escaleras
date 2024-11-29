// RegisterScreen.kt
package com.example.avance2.composables

import EnumLogin
import android.content.Intent
import android.widget.Toast
import com.example.avance2.R
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterScreen(onRegistrationComplete: () -> Unit) {
    val context = LocalContext.current  // Obtener el contexto

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000428))
    ) {
        Image(
            painter = painterResource(id = R.drawable.gimnasio),
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
            Text("Registro", fontSize = 32.sp, color = Color.White)

            Spacer(modifier = Modifier.height(32.dp))

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

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        FirebaseAuth.getInstance()
                            .createUserWithEmailAndPassword(username, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Mostrar un mensaje de éxito
                                    Toast.makeText(
                                        context,
                                        "Registro exitoso. Por favor inicia sesión.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    // Llamar a la página inicial de login
                                    onRegistrationComplete()
                                } else {
                                    // Mostrar el error de Firebase
                                    val error = task.exception?.localizedMessage ?: "Error desconocido"
                                    Toast.makeText(
                                        context,
                                        "Fallo al registrarse: $error",
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
                Text("Registrarse", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "¿Ya tienes cuenta? Inicia sesión",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.clickable { onRegistrationComplete() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    RegisterScreen(onRegistrationComplete = {})
}
