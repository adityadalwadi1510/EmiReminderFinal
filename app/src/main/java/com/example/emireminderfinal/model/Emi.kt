package com.example.emireminderfinal.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "emi_table")
data class Emi(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val emiName: String,
    val emiRs: String,
    val dueDate: String,
    val category: String,
    val descrition: String,
    val emiDuration: String,
    val completed: Int,
    val totalCompeleted: Int
) : Parcelable

