package com.example.calculatorapp.data.repository

import com.example.calculatorapp.data.locale.Operation
import com.example.calculatorapp.data.locale.OperationDao
import com.example.calculatorapp.domain.repository.OperationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OperationRepositoryImpl @Inject constructor(private val dao: OperationDao) : OperationRepository {
    override suspend fun insertOperation(operation: Operation) {
        dao.insertOperation(operation)
    }

    override fun getOperations(): Flow<List<Operation>> {
        return dao.getOperations()
    }

    override suspend fun deleteOperations() {
        return dao.deleteOperations()
    }
}