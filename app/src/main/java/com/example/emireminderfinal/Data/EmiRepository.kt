package com.example.emireminderfinal.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao

class EmiRepository(private val emiDao: EmiDao) {

    val readAllData:LiveData<List<Emi>> = emiDao.readEmi()

    suspend fun addEmi(emi: Emi){
        emiDao.addEmi(emi)
    }
}