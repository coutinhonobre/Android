package com.github.maxapp.presentation.clientes.ui.dados

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.maxapp.R
import com.github.maxapp.model.Cliente
import com.github.maxapp.model.Contato
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dados_clientes.*
import kotlinx.android.synthetic.main.fragment_dados.*
import org.jetbrains.anko.doAsync

class DadosFragment : Fragment() {

    private lateinit var dadosViewModel: DadosViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dados, container, false)


        return root
    }

    override fun onResume() {
        super.onResume()

        dadosViewModel =
            ViewModelProviders.of(this).get(DadosViewModel::class.java)

        dadosViewModel.getClientes().observe(this, Observer {
            it.forEach {c ->
                setDataView(c)
            }
        })

        swipeRefreshDadosCliente.setOnRefreshListener {
            doAsync {  dadosViewModel.refresData() }
            swipeRefreshDadosCliente.isRefreshing = false
        }

        dadosViewModel.getContatos().observe(this, Observer {
            gerenciarRecyclerView(it)
        })


    }

    private fun setDataView(c: Cliente) {
        dadosClienteRazaoSocial.text = c?.razaoSocial
        dadosClienteNomeFantasia.text = c?.nomeFantasia
        dadosClienteCpf.text = ""
        dadosClienteCnpj.text = c?.cnpj
        dadosClienteRamoAtividade.text = c?.ramoAtividade
        dadosClienteEndereco.text = c?.endereco



        fragmentDadosClienteBtn.setOnClickListener {

            Snackbar.make(
                fragmenteDadosCliente,
                c.status.toString(),
                Snackbar.LENGTH_LONG
            ).setAction(
                getString(R.string.fechar)
            ) {
                fragmenteDadosCliente.setBackgroundColor(Color.parseColor("#f2f2f2"))
            }.show()
        }

    }

    private fun gerenciarRecyclerView(c: List<Contato>) {
        var contatos = c
        fragmenteDadosClienteRecyclerView.layoutManager = LinearLayoutManager(this.context)
        fragmenteDadosClienteRecyclerView.adapter = DadosClienteAdapter(c)
    }

}