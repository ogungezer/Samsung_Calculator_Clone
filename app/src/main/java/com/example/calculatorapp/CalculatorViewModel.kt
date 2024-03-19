package com.example.calculatorapp

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.DecimalFormat
import kotlin.math.absoluteValue
import kotlin.math.nextDown
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class CalculatorViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(CalculatorUiState())
    val uiState : StateFlow<CalculatorUiState> = _uiState.asStateFlow()


    fun onEvent(event: OperationEvent){
        when(event){
            is OperationEvent.Clear -> clearExecute()
            is OperationEvent.Parenthesis -> parenthesisExecute()
            is OperationEvent.Delete -> deleteExecute()
            is OperationEvent.Calculate -> calculateExecute()
            is OperationEvent.Decimal -> decimalExecute()
            is OperationEvent.EnterNumber -> enterNumberExecute(event.number)
            is OperationEvent.Operation -> operationExecute(event.operationType)
        }
    }

    private fun operationExecute(operationType: OperationType) {
        if(uiState.value.numberOne.isNotBlank() && uiState.value.operation == null){
           _uiState.update { it.copy(operation = operationType)}
        }
    }

    private fun enterNumberExecute(number: Int) {
        if(uiState.value.operation == null){
            _uiState.update { it.copy(numberOne = uiState.value.numberOne + number) }
        } else {
            _uiState.update { it.copy(numberTwo = uiState.value.numberTwo + number) }
            printResult()
        }
    }

    private fun printResult() { //light gray result (bottom one)
        val numberOne = uiState.value.numberOne.toDoubleOrNull()
        val numberTwo = uiState.value.numberTwo.toDoubleOrNull()
        if(numberOne != null && uiState.value.operation != null && numberTwo != null){
            val result = when(uiState.value.operation){
                OperationType.Addition -> {
                    numberOne + numberTwo
                }
                OperationType.Division -> {
                    numberOne / numberTwo
                }
                OperationType.Mod -> {
                    numberOne % numberTwo
                }
                OperationType.Multiply -> {
                    numberOne * numberTwo
                }
                OperationType.Subtraction -> {
                    numberOne - numberTwo
                }
                null -> return
            }
            println(result.toString()) //result
            val decimal = result.toString().split('.').getOrElse(1){""} //
            println("Ondalık kısım: $decimal")
            val decimalCount = decimal.length
            println("Sıfır sayısı : $decimalCount")
            if(decimalCount > 6){
                if(decimal.count{ it == '0'} > 4){
                    _uiState.update { it.copy(result = String.format("%.4f", result).toDouble())} //
                } else {
                    _uiState.update { it.copy(result = DecimalFormat("#,###.##").format(result).toDouble())} //
                }
            } else {
                _uiState.update { it.copy(result = result)}
            }
        }
    }

    private fun decimalExecute() {
        if(uiState.value.operation == null && !uiState.value.numberOne.contains(",") && uiState.value.numberOne.isNotBlank()){
            _uiState.update { it.copy(numberOne = uiState.value.numberOne + "," )}
            return
        }
        if(uiState.value.operation != null && !uiState.value.numberTwo.contains(",") && uiState.value.numberTwo.isNotBlank()){
            _uiState.update { it.copy(numberTwo = uiState.value.numberTwo + ",") }
        }
    }

    private fun calculateExecute() {
        val numberOne = uiState.value.numberOne.replace(",",".").toDoubleOrNull()
        val numberTwo = uiState.value.numberTwo.replace(",",".").toDoubleOrNull()
        if(numberOne != null && uiState.value.operation != null && numberTwo != null){
            val result = when(uiState.value.operation){
                OperationType.Addition -> {
                    numberOne + numberTwo
                }
                OperationType.Division -> {
                    numberOne / numberTwo
                }
                OperationType.Mod -> {
                    numberOne % numberTwo
                }
                OperationType.Multiply -> {
                    numberOne * numberTwo
                }
                OperationType.Subtraction -> {
                    numberOne - numberTwo
                }
                null -> return
            }
            if((result - result.toInt()) == 0.0){
                _uiState.update { it.copy(result = null, numberOne = result.toInt().toString(), numberTwo = "", operation = null)}
            } else {
                val decimal = result.toString().split(",").getOrElse(1){""}
                val decimalCount = decimal.length
                if(decimalCount > 6){
                    if(decimal.count{ it == '0'} > 4){
                        _uiState.update { it.copy(result = null, numberOne = String.format("%,2f", result), numberTwo = "", operation = null)}
                    } else {
                        _uiState.update { it.copy(result = null, numberOne = String.format("%,4f", result), numberTwo = "", operation = null)}
                    }
                } else {
                _uiState.update { it.copy(result = null, numberOne = result.toString(), numberTwo = "", operation = null)}
                }
            }
        }

    }

    private fun deleteExecute() {
        if(uiState.value.numberTwo.isNotBlank() && uiState.value.operation != null){
            _uiState.update { it.copy(numberTwo = uiState.value.numberTwo.dropLast(1), result = null) }
            printResult()
            return
        }
        if(uiState.value.numberTwo.isBlank() && uiState.value.operation != null){
            _uiState.update { it.copy(operation = null, result = null)}
            printResult()
            return
        }
        if(uiState.value.operation == null && uiState.value.numberOne.isNotBlank()){
            _uiState.update { it.copy(numberOne = uiState.value.numberOne.dropLast(1), result = null) }
            printResult()
            return
        }
    }

    private fun parenthesisExecute() {
        return
    }

    private fun clearExecute() {
        _uiState.value = CalculatorUiState()
    }
}