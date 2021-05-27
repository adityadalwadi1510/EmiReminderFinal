package com.example.emireminderfinal.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmiDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend  fun addEmi(emi: Emi)

    @Query(value = "SELECT * FROM  emi_table")
    fun readEmi():LiveData<List<Emi>>
}