package com.github.maxapp.presentation.clientes.ui.historicopedidos

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.maxapp.apinetwork.ApiRetrofit
import com.github.maxapp.apinetwork.pedidos.Pedido
import com.github.maxapp.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HistoricoPedidosViewModel(application: Application) : AndroidViewModel(application) {

    private val appRepository = AppRepository(application)

    init {
        appRepository.fetchDataFromServerPedidos()
    }


    fun getPedidos() = appRepository.getAllPedidos()

    fun getPedidos(s1: String) = appRepository.getAllPedidoRcaLive(s1)


    fun refresData(){
        appRepository.fetchDataFromServerPedidos()
    }
}