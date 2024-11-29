// MainActivity.kt
package com.example.avance2

import EnumLogin
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.avance2.composables.HomeScreen
import com.example.avance2.composables.LoginScreen
import com.example.avance2.composables.RegisterScreen
import com.example.avance2.ui.theme.Avance2Theme
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Avance2Theme {
                AppMainScreen()
            }
        }
        val analytics = FirebaseAnalytics.getInstance(this);
        val bundle = Bundle()
        bundle.putString("message", "Integración de Firebase completa")
        analytics.logEvent("MainActivity", Bundle())
    }
}

@Composable
fun AppMainScreen() {
    // Estado para controlar la pantalla actual usando EnumLogin
    var currentScreen by remember { mutableStateOf(EnumLogin.INGRESAR) }

    when (currentScreen) {
        EnumLogin.INGRESAR -> {
            LoginScreen(
                onLoginSuccess = { currentScreen = EnumLogin.ACTUALIZAR }, // Cambia a HomeScreen al hacer clic en "Ingresar"
                onRegisterClick = { currentScreen = EnumLogin.REGISTRAR } // Cambia a RegisterScreen al hacer clic en "Registrarse"
            )
        }
        EnumLogin.ACTUALIZAR -> {
            HomeScreen() // Mostrar HomeScreen cuando el usuario está logueado
        }
        EnumLogin.REGISTRAR -> {
            RegisterScreen(
                onRegistrationComplete = { currentScreen = EnumLogin.INGRESAR } // Vuelve a LoginScreen tras completar el registro
            )
        }
    }
}