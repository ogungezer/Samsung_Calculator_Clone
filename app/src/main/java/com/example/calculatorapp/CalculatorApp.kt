package com.example.calculatorapp

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorapp.component.ButtonGrid
import com.example.calculatorapp.component.CustomText
import com.example.calculatorapp.component.IconRow
import com.example.calculatorapp.ui.theme.DarkBackGround
import com.example.calculatorapp.ui.theme.LightBackGround

@Composable
fun CalculatorApp() {

    val viewModel : CalculatorViewModel = viewModel()
    val state = viewModel.uiState.collectAsState().value
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(modifier = Modifier
            .weight(1.3f)
            .fillMaxWidth()
        ){
            CustomText(state = state)
            state.result?.let {
                Text(
                    text = if(state.result - state.result.toInt() == 0.0) state.result.toInt().toString() else state.result.toString().replace(".",","),
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.surfaceTint,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 36.dp, bottom = 120.dp)
                )
            }
            Box(modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 36.dp)){
                IconRow(onEvent = viewModel::onEvent, state = state)
            }

            Divider(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 20.dp)
                .padding(bottom = 14.dp),
                color = MaterialTheme.colorScheme.scrim,
                thickness = 0.8.dp
            )

        }

        Box(modifier = Modifier
            .weight(1.6f)
            .padding(16.dp)
            .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            ButtonGrid(viewModel::onEvent)
        }
    }
}