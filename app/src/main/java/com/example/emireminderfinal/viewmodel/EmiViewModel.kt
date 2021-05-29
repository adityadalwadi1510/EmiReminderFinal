package com.example.emireminderfinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.emireminderfinal.Data.EmiDatabase
import com.example.emireminderfinal.model.Emi
import com.example.emireminderfinal.repository.EmiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmiViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Emi>>
    private val emiRepository: EmiRepository

    init {
        val emiDao =
            EmiDatabase.getDatabase(
                application
            ).emiDao()

        emiRepository = EmiRepository(emiDao)

        readAllData = emiRepository.readAllData
    }

    fun addUser(emi: Emi) {
        viewModelScope.launch(Dispatchers.IO) { emiRepository.addEmi(emi) }

    }

    fun updateEmi(emi: Emi) {
        viewModelScope.launch(Dispatchers.IO) {
            emiRepository.updateEmi(emi)
        }
    }

    fun deleteEmi(emi: Emi) {
        viewModelScope.launch(Dispatchers.IO) {
            emiRepository.deleteEmi(emi)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            emiRepository.deleteAll()
        }
    }
}