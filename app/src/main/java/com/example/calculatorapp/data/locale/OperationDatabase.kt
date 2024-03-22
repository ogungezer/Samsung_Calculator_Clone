package com.example.calculatorapp.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Operation::class], version = 1, exportSchema = false)
abstract class OperationDatabase : RoomDatabase() {
    abstract fun dao() : OperationDao
}
