package com.example.calculatorapp.presentation

sealed class OperationType(val symbol : String){
    object Addition : OperationType(symbol = "+")
    object Subtraction : OperationType(symbol = "-")
    object Multiply : OperationType(symbol = "x")
    object Division : OperationType(symbol = "÷")
    object Mod : OperationType(symbol = "%")

}