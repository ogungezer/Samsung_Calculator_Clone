package com.example.calculatorapp.data.locale

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOperation(operation : Operation)

    @Query("SELECT * FROM Operation")
    fun getOperations() : Flow<List<Operation>>

    @Query("DELETE FROM Operation")
    suspend fun deleteOperations()



}