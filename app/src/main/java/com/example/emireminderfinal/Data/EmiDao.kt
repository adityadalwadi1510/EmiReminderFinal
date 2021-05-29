package com.example.emireminderfinal.Data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.emireminderfinal.model.Emi

@Dao
interface EmiDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEmi(emi: Emi)


    @Query(value = "SELECT * FROM  emi_table WHERE totalCompeleted = 0")
    fun readEmi(): LiveData<List<Emi>>


    @Update
    suspend fun updateEmi(emi: Emi)

    @Delete
    suspend fun deleteEmi(emi: Emi)

    @Query(value = "DELETE FROM emi_table")
    suspend fun deleteAllEmi()

}