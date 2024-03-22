package com.example.calculatorapp.presentation

sealed class OperationEvent{
    object Clear : OperationEvent()
    object Delete : OperationEvent()
    object Calculate : OperationEvent()
    object Decimal : OperationEvent()
    object Parenthesis : OperationEvent()
    object OpenHistory : OperationEvent()
    object ClearHistory : OperationEvent()
    data class EnterNumber(val number : Int) : OperationEvent()
    data class Operation(val operationType: OperationType) : OperationEvent()
}
