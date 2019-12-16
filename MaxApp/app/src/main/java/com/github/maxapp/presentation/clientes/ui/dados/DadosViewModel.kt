package com.github.maxapp.presentation.clientes.ui.dados

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.maxapp.apinetwork.ApiRetrofit
import com.github.maxapp.apinetwork.clientes.Cliente
import com.github.maxapp.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class DadosViewModel(application: Application) : AndroidViewModel(application) {

    private val appRepository = AppRepository(application)

    init {
        appRepository.fetchDataFromServerClientes()
    }


    fun getClientes() = appRepository.getAllClientes()

    fun getContatos() = appRepository.getAllContatos()

    fun refresData(){
        appRepository.fetchDataFromServerClientes()
    }


}