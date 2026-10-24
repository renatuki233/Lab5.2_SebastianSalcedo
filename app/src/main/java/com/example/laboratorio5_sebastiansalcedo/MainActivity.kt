package com.example.laboratorio5_sebastiansalcedo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            MaterialTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    LoginScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {

    // Estado principal de la pantalla
    var username by remember {
        mutableStateOf("")
    }

    // Validación principal del laboratorio
    val isValid =
        username.length >= 3

    val message =
        if (isValid) {
            "Nombre aceptado"
        } else {
            "Nombre muy corto (mín. 3)"
        }

    val messageColor =
        if (isValid) {
            Color(0xFF2E7D32)
        } else {
            Color.Red
        }

    // Reto adicional:
    // el botón solo se habilita con 5 caracteres o más
    val buttonEnabled =
        username.length >= 5

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement =
        Arrangement.Center
    ) {

        Text(
            text = "Validador de Credenciales",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        OutlinedTextField(
            value = username,

            onValueChange = {
                username = it
            },

            label = {
                Text("Nombre de Usuario")
            },

            modifier =
            Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = message,
            color = messageColor,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                // Acción del botón para el reto
            },

            enabled = buttonEnabled,

            modifier =
            Modifier.fillMaxWidth()
        ) {

            Text("Continuar")
        }
    }
}