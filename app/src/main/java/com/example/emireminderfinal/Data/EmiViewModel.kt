package com.example.emireminderfinal.Data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmiViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Emi>>
    private val emiRepository:EmiRepository
    init {
        val emiDao =EmiDatabase.getDatabase(application).emiDao()

        emiRepository = EmiRepository(emiDao)

        readAllData=emiRepository.readAllData
    }

    fun addUser(emi:Emi){
        viewModelScope.launch (Dispatchers.IO){  emiRepository.addEmi(emi) }

    }

}