package com.example.emireminderfinal.repository

import androidx.lifecycle.LiveData
import com.example.emireminderfinal.Data.EmiDao
import com.example.emireminderfinal.model.Emi

class EmiRepository(private val emiDao: EmiDao) {

    val readAllData:LiveData<List<Emi>> = emiDao.readEmi()

    suspend fun addEmi(emi: Emi){
        emiDao.addEmi(emi)
    }

    suspend fun updateEmi(emi: Emi){
        emiDao.updateEmi(emi)
    }

    suspend fun deleteEmi(emi: Emi){
        emiDao.deleteEmi(emi)
    }

    suspend fun deleteAll(){
        emiDao.deleteAllEmi()
    }
}