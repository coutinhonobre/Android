package com.github.maxapp.presentation.menuprincipal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.maxapp.R
import com.github.maxapp.presentation.clientes.ClientesActivity
import com.github.maxapp.utilitarios.ConnectivityHelper
import kotlinx.android.synthetic.main.activity_main.*


class ServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.app_name)

        menuPrincipalFragmentClientes.setOnClickListener {
            startActivity(Intent(this, ClientesActivity::class.java))
        }


        var conectado = this?.let { ConnectivityHelper.isConnectedToNetwork(it) }

        if (conectado!!)
            menuPrincipalVersaoConnexao.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,
                R.drawable.ic_maxima_nuvem_conectado, 0)
        else
            menuPrincipalVersaoConnexao.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,
                R.drawable.ic_maxima_nuvem_desconectado, 0)


    }
}
