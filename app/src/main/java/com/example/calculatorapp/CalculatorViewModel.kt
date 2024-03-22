package com.example.calculatorapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculatorapp.data.locale.Operation
import com.example.calculatorapp.domain.repository.OperationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

//Repoyu viewmodelde uygulama işi kaldı. Repodaki metodları kullanıcaz viewmodelde sonrasında ui de list şeklinde getOperations çağırıcaz.
@HiltViewModel
class CalculatorViewModel @Inject constructor(private val repo : OperationRepository) : ViewModel() {

    private var _uiState = MutableStateFlow(CalculatorUiState())
    val uiState : StateFlow<CalculatorUiState> = _uiState.asStateFlow()


    init {
        fetchHistoryDataInit()
    }

    fun onEvent(event: OperationEvent){
        when(event){
            is OperationEvent.Clear -> clearExecute()
            is OperationEvent.Parenthesis -> parenthesisExecute()
            is OperationEvent.Delete -> deleteExecute()
            is OperationEvent.Calculate -> calculateExecute()
            is OperationEvent.Decimal -> decimalExecute()
            is OperationEvent.EnterNumber -> enterNumberExecute(event.number)
            is OperationEvent.Operation -> operationExecute(event.operationType)
            is OperationEvent.OpenHistory -> historyExecute()
            is OperationEvent.ClearHistory -> clearHistoryExecute()
        }
    }

    private fun clearHistoryExecute() {
        viewModelScope.launch {
            repo.deleteOperations()
            _uiState.update { it.copy(isHistoryTabOpen = false)}
        }

    }

    private fun fetchHistoryDataInit(){
        viewModelScope.launch {
            repo.getOperations().collect{ historyList ->
                _uiState.update { it.copy(historyOperationList = historyList ) }
            }
        }
    }

    private fun historyExecute() {
        if(uiState.value.isHistoryTabOpen){
            viewModelScope.launch {
                repo.getOperations().collect{ historyList ->
                    _uiState.update { it.copy(historyOperationList = historyList ) }
                }
            }
        }
    }

    private fun operationExecute(operationType: OperationType) {
        if(uiState.value.numberOne.isNotBlank() && uiState.value.operation == null){
           _uiState.update { it.copy(operation = operationType, operationSymbol = operationType.symbol)}
        }
    }

    private fun enterNumberExecute(number: Int) {
        if(uiState.value.operation == null){
            if(uiState.value.numberOne.length > 14){
               return
            } else {
                _uiState.update { it.copy(numberOne = uiState.value.numberOne + number) }
            }
        } else {
            if(uiState.value.numberTwo.length > 14){
               return
            } else {
                _uiState.update { it.copy(numberTwo = uiState.value.numberTwo + number) }
                printResult()
            }
        }
    }

    private fun printResult() { //light gray result (bottom one)
        val numberOne = uiState.value.numberOne.replace(',','.').toBigDecimalOrNull()
        val numberTwo = uiState.value.numberTwo.replace(',','.').toBigDecimalOrNull()
        if(numberOne != null && uiState.value.operation != null && numberTwo != null){
            val result = when(uiState.value.operation){
                OperationType.Addition -> {
                    (numberOne + numberTwo).toEngineeringString().replace('.',',')
                }
                OperationType.Division -> {
                    if(numberTwo == BigDecimal.ZERO){
                        return
                    } else {
                        (numberOne.divide(numberTwo,4,RoundingMode.HALF_UP)).toEngineeringString().replace('.',',')

                    }
                }
                OperationType.Mod -> {
                   (numberOne % numberTwo).toEngineeringString().replace('.',',')
                }
                OperationType.Multiply -> {
                    (numberOne * numberTwo).toEngineeringString().replace('.',',')
                }
                OperationType.Subtraction -> {
                    (numberOne - numberTwo).toEngineeringString().replace('.',',')
                }
                null -> return
            }
            if((result.replace(',','.').toDouble() - result.replace(',','.').split('.').getOrElse(0){""}.toDouble()) == 0.0){
                _uiState.update { it.copy(result = result.split(',').getOrElse(0){""})} //HATA BURDA BAK
            } else {
                //println(result)
                val decimal = result.split(',').getOrElse(1) { "" }
                val integer = result.split(',').getOrElse(0) { "" }//
                //println("Ondalık kısım: $decimal")
                //println("Int kısım: $integer")
                val decimalCount = decimal.length
                //println("Sıfır sayısı : $decimalCount")
                if (decimalCount > 4) {
                    if (decimal.count { it == '0' } > 4) {
                        _uiState.update { it.copy(result = "$integer,${decimal.take(2)}") } //
                    } else {
                        _uiState.update { it.copy(result = "$integer,${decimal}") } //
                    }
                } else {
                    _uiState.update { it.copy(result = "$integer,${decimal}") }
                }
            }
        }
    }

    private fun decimalExecute() {
        if(uiState.value.operation == null && !uiState.value.numberOne.contains(',') && uiState.value.numberOne.isNotBlank()){
            _uiState.update { it.copy(numberOne = uiState.value.numberOne + ',' )}
            return
        }
        if(uiState.value.operation != null && !uiState.value.numberTwo.contains(',') && uiState.value.numberTwo.isNotBlank()){
            _uiState.update { it.copy(numberTwo = uiState.value.numberTwo + ',') }
        }
    }

    private fun calculateExecute() {
        viewModelScope.launch {
            val numberOne = uiState.value.numberOne.replace(',','.').toBigDecimalOrNull()
            val numberTwo = uiState.value.numberTwo.replace(',','.').toBigDecimalOrNull()
            if(numberOne != null && uiState.value.operation != null && numberTwo != null){
                val result = when(uiState.value.operation){
                    OperationType.Addition -> {
                        (numberOne + numberTwo).toString().replace('.',',')
                    }
                    OperationType.Division -> {
                        if(numberTwo == BigDecimal.ZERO){
                            return@launch
                        } else {
                            (numberOne.divide(numberTwo,4,RoundingMode.HALF_UP)).toEngineeringString().replace('.',',')

                        }
                    }
                    OperationType.Mod -> {
                        (numberOne % numberTwo).toString().replace('.',',')
                    }
                    OperationType.Multiply -> {
                        (numberOne * numberTwo).toString().replace('.',',')
                    }
                    OperationType.Subtraction -> {
                        (numberOne - numberTwo).toString().replace('.',',')
                    }
                    null -> return@launch
                }
                if((result.replace(',','.').toDouble() - result.replace(',','.').split('.').getOrElse(0){""}.toDouble()) == 0.0){
                    repo.insertOperation(operation = Operation(
                        num1 = uiState.value.numberOne,
                        num2 = uiState.value.numberTwo,
                        result = result.split(',').getOrElse(0){""},
                        operation = uiState.value.operationSymbol)
                    )
                    _uiState.update { it.copy(result = null, numberOne = result.split(',').getOrElse(0){""}, numberTwo = "", operation = null)}
                } else {
                    val decimal = result.split(',').getOrElse(1){""}
                    val integer = result.split(',').getOrElse(0){""}
                    val decimalCount = decimal.length
                    if(decimalCount > 4){
                        if(decimal.count{ it == '0'} > 4){
                            repo.insertOperation(operation = Operation(
                                num1 = uiState.value.numberOne,
                                num2 = uiState.value.numberTwo,
                                result = "$integer,${decimal.take(2)}",
                                operation = uiState.value.operationSymbol)
                            )
                            _uiState.update { it.copy(result = null, numberOne = "$integer,${decimal.take(2)}", numberTwo = "", operation = null)}
                        } else {
                            repo.insertOperation(operation = Operation(
                                num1 = uiState.value.numberOne,
                                num2 = uiState.value.numberTwo,
                                result = "$integer,$decimal",
                                operation = uiState.value.operationSymbol)
                            )
                            _uiState.update { it.copy(result = null, numberOne = "$integer,$decimal", numberTwo = "", operation = null)}
                        }
                    } else {
                        repo.insertOperation(operation = Operation(
                            num1 = uiState.value.numberOne,
                            num2 = uiState.value.numberTwo,
                            result = "$integer,$decimal",
                            operation = uiState.value.operationSymbol)
                        )
                        _uiState.update { it.copy(result = null, numberOne = "$integer,$decimal", numberTwo = "", operation = null)}
                    }
                }
            }
        }

    }

    private fun deleteExecute() {
        if(uiState.value.numberTwo.isNotBlank() && uiState.value.operation != null){
            _uiState.update { it.copy(numberTwo = uiState.value.numberTwo.dropLast(1), result = null, isTwoDeleted  = false) }
            printResult()
            return
        }
        if(uiState.value.numberTwo.isBlank() && uiState.value.operation != null){
            _uiState.update { it.copy(operation = null, result = null, isTwoDeleted  = true)}
            printResult()
            return
        }
        if(uiState.value.operation == null && uiState.value.numberOne.isNotBlank()){
            _uiState.update { it.copy(numberOne = uiState.value.numberOne.dropLast(1), result = null, isTwoDeleted  = false) }
            printResult()
            return
        }
    }

    private fun parenthesisExecute() {
        return
    }

    private fun clearExecute() {
        _uiState.update { it.copy(numberOne = "", numberTwo = "", operation = null, result = "", operationSymbol = "", isTwoDeleted = false, isShowingToast = false) }
    }

    fun inputValidation() {
        println("1.sayı uzunluğu: ${uiState.value.numberOne.length}")
        println("2.sayı uzunluğu: ${uiState.value.numberTwo.length}")
        if(uiState.value.operation == null){
            if(uiState.value.numberOne.length > 14){
                _uiState.update { it.copy(isShowingToast = true) }
            } else {
                _uiState.update { it.copy(isShowingToast = false) }
            }
        } else {
            if(uiState.value.numberTwo.length > 14){
                _uiState.update { it.copy(isShowingToast = true) }
            } else {
                _uiState.update { it.copy(isShowingToast = false) }
            }
        }
        println("Mesaj Gösterilsin mi?: ${uiState.value.isShowingToast}")
    }

    fun updateTabState(){
        _uiState.update { it.copy(isHistoryTabOpen = !uiState.value.isHistoryTabOpen) }
    }
}