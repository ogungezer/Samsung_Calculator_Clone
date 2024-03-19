package com.example.calculatorapp


data class CalculatorUiState(
    val result : Double? = null,
    val numberOne : String = "",
    val numberTwo : String = "",
    //val isFirstParenthesis : Boolean = true,
    val operation : OperationType? = null,

)
