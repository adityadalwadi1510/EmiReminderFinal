package com.example.emireminderfinal.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration

@Entity(tableName = "emi_table")
data class Emi(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val emiName:String,
    val emiRs:String,
    val dueDate:String,
    val category:String,
    val descrition:String,
    val emiDuration: String
)

