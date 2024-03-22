package com.example.calculatorapp.data.locale

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Operation(
    val num1 : String = "",
    val num2 : String = "",
    val result : String = "",
    val operation : String = "",
    @PrimaryKey(autoGenerate = true)
    val id : Int? = null
)
