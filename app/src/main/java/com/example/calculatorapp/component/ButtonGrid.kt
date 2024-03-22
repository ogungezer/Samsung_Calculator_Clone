package com.example.calculatorapp.component

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapp.OperationEvent
import com.example.calculatorapp.OperationType

@Composable
fun ButtonGrid(onEvent : (OperationEvent) -> Unit){

    val context = LocalContext.current

    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(16.dp)){
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().weight(1f)){
            CircleButton(
                background = MaterialTheme.colorScheme.secondary,
                content = "C",
                contentColor = MaterialTheme.colorScheme.surface,
                onClick ={
                    onEvent(OperationEvent.Clear)
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.secondary,
                content = "( )",
                contentColor = MaterialTheme.colorScheme.onPrimary,
                contentSize = 22.sp,
                onClick ={
                    onEvent(OperationEvent.Parenthesis)
                    Toast.makeText(context,"Bu işlev şuanda yok",Toast.LENGTH_SHORT).show()
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.secondary,
                content = "%",
                contentColor = MaterialTheme.colorScheme.onPrimary,
                onClick ={
                    onEvent(OperationEvent.Operation(OperationType.Mod))
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.secondary,
                content = "÷",
                contentColor = MaterialTheme.colorScheme.onPrimary,
                contentSize = 37.sp,
                onClick ={
                    onEvent(OperationEvent.Operation(OperationType.Division))
                }

            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().weight(1f)){
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = "7",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                onClick = {
                    onEvent(OperationEvent.EnterNumber(7))
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = "8",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                onClick = {
                    onEvent(OperationEvent.EnterNumber(8))
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = "9",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                onClick = {
                    onEvent(OperationEvent.EnterNumber(9))
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.secondary,
                content = "×",
                contentColor = MaterialTheme.colorScheme.onPrimary,
                contentSize = 37.sp,
                onClick = {
                    onEvent(OperationEvent.Operation(OperationType.Multiply))
                }
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().weight(1f)){
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = "4",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                onClick = {
                    onEvent(OperationEvent.EnterNumber(4))
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = "5",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                onClick = {
                    onEvent(OperationEvent.EnterNumber(5))
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = "6",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                onClick = {
                    onEvent(OperationEvent.EnterNumber(6))
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.secondary,
                content = "—",
                contentColor = MaterialTheme.colorScheme.onPrimary,
                contentSize = 35.sp,
                onClick = {
                    onEvent(OperationEvent.Operation(OperationType.Subtraction))
                }
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().weight(1f)){
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = "1",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                onClick = {
                    onEvent(OperationEvent.EnterNumber(1))
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = "2",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                onClick = {
                    onEvent(OperationEvent.EnterNumber(2))
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = "3",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                onClick = {
                    onEvent(OperationEvent.EnterNumber(3))
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.secondary,
                content = "+",
                contentColor = MaterialTheme.colorScheme.onPrimary,
                contentSize = 37.sp,
                onClick = {
                    onEvent(OperationEvent.Operation(OperationType.Addition))
                }
            )
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().weight(1f)){
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = "+/-",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                contentSize = 29.sp,
                onClick = {
                    Toast.makeText(context,"Bu işlev şuanda yok",Toast.LENGTH_SHORT).show()
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = "0",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                onClick = {
                    onEvent(OperationEvent.EnterNumber(0))
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.primary,
                content = ",",
                contentColor = MaterialTheme.colorScheme.onSecondary,
                onClick = {
                    onEvent(OperationEvent.Decimal)
                }
            )
            CircleButton(
                background = MaterialTheme.colorScheme.tertiary,
                content = "=",
                contentColor = Color.White,
                contentSize = 47.sp,
                onClick = {
                    onEvent(OperationEvent.Calculate)
                }
            )
        }
    }
}