package com.example.calculatorapp

import com.example.calculatorapp.data.locale.Operation


data class CalculatorUiState(
    val result : String? = "",
    val numberOne : String = "",
    val numberTwo : String = "",
    //val isFirstParenthesis : Boolean = true,
    val operation : OperationType? = null,
    val operationSymbol : String = "",
    val isShowingToast : Boolean = false,
    val isTwoDeleted : Boolean = false,

    val isHistoryTabOpen : Boolean = false,
    val historyOperationList : List<Operation> = emptyList()

)
