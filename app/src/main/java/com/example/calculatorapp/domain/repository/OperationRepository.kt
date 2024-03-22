package com.example.calculatorapp.domain.repository

import com.example.calculatorapp.data.locale.Operation

interface OperationRepository {

    suspend fun insertOperation(operation: Operation)

    fun getOperations() : kotlinx.coroutines.flow.Flow<List<Operation>>

    suspend fun deleteOperations()



}