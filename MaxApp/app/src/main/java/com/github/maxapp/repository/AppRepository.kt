package com.github.maxapp.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.maxapp.apinetwork.ApiRetrofit
import com.github.maxapp.apinetwork.ApiService
import com.github.maxapp.apinetwork.clientes.Clientes
import com.github.maxapp.apinetwork.pedidos.Pedidos
import com.github.maxapp.database.AppDataBase
import com.github.maxapp.model.Cliente
import com.github.maxapp.model.Contato
import com.github.maxapp.model.Pedido
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class AppRepository(val context: Context) {


    val clienteLiveData: MutableLiveData<com.github.maxapp.apinetwork.clientes.Cliente> =
        MutableLiveData()
    val pedidoLiveData: MutableLiveData<Pedidos> = MutableLiveData()
    var getClientes = ApiRetrofit.RETROFIT_SERVICE.getClientes()
    var getPedidos = ApiRetrofit.RETROFIT_SERVICE.getPedidos()

    private val database = AppDataBase.getInstance(context)


    fun getAllClientes() = database.Dao().getAllLiveCliente()
    fun getAllClientes(codigo: String) = database.Dao().getAllClienteID(codigo)

    fun getAllContatos() = database.Dao().getAllLiveContato()
    fun getAllContatos(nome: String, cliente: Long) = database.Dao().getAllContatoClienteNome(nome, cliente)

    fun getAllPedidos() = database.Dao().getAllLivePedido()
    fun getAllPedidoRca(rca: String) = database.Dao().getAllPedidoRca(rca)
    fun getAllPedidoRcaLive(s1: String) = database.Dao().getAllPedidoRcaLive(s1)

    fun setInsertCliente(cliente: Cliente) {
        database.Dao().addSingleCliente(cliente)
    }

    fun setInsertContato(contato: Contato) {
        database.Dao().addSingleContato(contato)
    }

    fun setInsertPedido(pedido: Pedido) {
        database.Dao().addSinglePedido(pedido)
    }

    fun fetchDataFromServerPedidos() {

        val listResult = getPedidos.enqueue(object : Callback<Pedidos> {
            override fun onFailure(call: Call<Pedidos>, t: Throwable) {
                Log.e("Falha", "Requisicao Falou")
            }

            override fun onResponse(call: Call<Pedidos>, response: Response<Pedidos>) {
                if (response.code() == 200) {
                    response.body()?.let { ps ->
                        ps!!.pedidos!!.forEach {
                            val pedido: Pedido = Pedido(
                                numeroPedRca = it.numeroPedRca.toString(),
                                codigoCliente = it.codigoCliente.toString(),
                                nomeCliente = it.nomeCliente.toString(),
                                status = it.status.toString(),
                                critica = it.critica.toString(),
                                tipo = it.tipo.toString(),
                                legendas = it.legendas.toString()
                            )
                            doAsync {
                                if (getAllPedidoRca(pedido.numeroPedRca).isEmpty())
                                    setInsertPedido(pedido)
                            }

                        }

                    }
                }
            }

        })




        Log.i("Sucesso", "Requisicao Sucesso")


    }

    fun fetchDataFromServerClientes() {

        val listResult = getClientes.enqueue(object : Callback<Clientes> {
            override fun onFailure(call: Call<Clientes>, t: Throwable) {
                Log.e("Falha", "Requisicao Falou")
            }

            override fun onResponse(call: Call<Clientes>, response: Response<Clientes>) {
                if (response.code() == 200) {
                    var clienteID: Long = 0
                    response.body()?.cliente.let { c ->
                        val cliente = Cliente(
                            c!!.id!!.toLong(),
                            c.codigo.toString(),
                            c.razaoSocial.toString(),
                            c.nomeFantasia.toString(),
                            c.cnpj.toString(),
                            c.ramoAtividade.toString(), c.endereco.toString(), c.status.toString()
                        )
                        doAsync {
                            if (getAllClientes(cliente.codigo).isEmpty()) {
                                setInsertCliente(cliente)
                                clienteID = c!!.id!!
                            }
                        }
                    }
                    response.body()?.cliente?.contatos.let {
                        it!!.forEach { con ->
                            val contato = Contato(
                                nome = con.nome.toString(),
                                telefone = con.telefone.toString(),
                                celular = con.celular.toString(),
                                conjuge = con.conjuge.toString(),
                                tipo = con.tipo.toString(),
                                eMail = con.eMail.toString(),
                                dataNascimento = con.dataNascimento.toString(),
                                dataNascimentoConjuge = con.dataNascimentoConjuge.toString(),
                                clienteId = clienteID
                            )
                            doAsync {
                                if (getAllContatos(contato.nome, clienteID).isEmpty())
                                    setInsertContato(contato)
                            }
                        }

                    }
                }
            }
        })


        Log.i("Sucesso", "Requisicao Sucesso")


    }


}