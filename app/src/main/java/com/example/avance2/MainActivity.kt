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
import com.example.avance2.views.composables.HomeScreen
import com.example.avance2.views.composables.LoginScreen
import com.example.avance2.views.composables.RegisterScreen
import com.example.avance2.enums.EnumHome
import com.example.avance2.ui.theme.Avance2Theme
import com.example.avance2.views.composables.SnakeLaddersBoard
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
    var currentScreen by remember { mutableStateOf(EnumLogin.INGRESAR) }
    val currentAction = remember { mutableStateOf(EnumHome.CREAR_PARTIDA) } // Cambiado a MutableState

    when (currentScreen) {
        EnumLogin.INGRESAR -> {
            LoginScreen(
                onLoginSuccess = { currentScreen = EnumLogin.ACTUALIZAR },
                onRegisterClick = { currentScreen = EnumLogin.REGISTRAR }
            )
        }
        EnumLogin.ACTUALIZAR -> {
            HomeScreen(
                currentAction = currentAction, // Pasa el estado mutable
                onLocalGameClick = { currentAction.value = EnumHome.JUGAR_PARTIDA },
                onCreateGameClick = { currentAction.value = EnumHome.CREAR_PARTIDA }
            )
            when (currentAction.value) {
                EnumHome.CREAR_PARTIDA -> {

                }
                EnumHome.JUGAR_PARTIDA -> {
                    SnakeLaddersBoard()
                }
                else -> {}
            }
        }
        EnumLogin.REGISTRAR -> {
            RegisterScreen(
                onRegistrationComplete = { currentScreen = EnumLogin.INGRESAR }
            )
        }
    }
}
