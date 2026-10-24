package com.example.laboratorio5_sebastiansalcedo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SmartHome()
                }
            }
        }
    }
}

@Composable
fun SmartHome() {

    // Log para observar recomposición del componente padre
    Log.d("RECOMPOSE", "DIBUJANDO PADRE (SmartHome)")

    // State Hoisting: el PADRE tiene la fuente única de verdad
    var isLightOn by remember {
        mutableStateOf(false)
    }

    // Estado independiente para el desafío
    var refreshCount by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Centro de Control Domótico",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        // Componente HIJO
        LightBulb(
            isOn = isLightOn
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text("Interruptor Principal")

            Switch(
                checked = isLightOn,
                onCheckedChange = {
                    isLightOn = it
                }
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Refrescos de UI: $refreshCount"
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {
                refreshCount++
            }
        ) {
            Text("Refrescar UI")
        }
    }
}

@Composable
fun LightBulb(
    isOn: Boolean
) {

    // Log para observar recomposición del componente hijo
    Log.d("RECOMPOSE", "DIBUJANDO HIJO (LightBulb)")

    Box(
        modifier = Modifier
            .size(120.dp)
            .background(
                color = if (isOn) {
                    Color.Yellow
                } else {
                    Color.Gray
                },
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = if (isOn) "ON" else "OFF",
            style = MaterialTheme.typography.headlineMedium,
            color = if (isOn) Color.Black else Color.White
        )
    }
}