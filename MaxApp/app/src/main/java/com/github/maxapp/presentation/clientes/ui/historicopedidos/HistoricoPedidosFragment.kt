package com.github.maxapp.presentation.clientes.ui.historicopedidos

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.maxapp.R
import kotlinx.android.synthetic.main.fragment_historico_pedidos.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.github.maxapp.model.Pedido
import org.jetbrains.anko.doAsync

class HistoricoPedidosFragment : Fragment() {

    private lateinit var historicoPedidosViewModel: HistoricoPedidosViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_historico_pedidos, container, false)
        return root
    }

    override fun onResume() {
        super.onResume()

        historicoPedidosViewModel = ViewModelProviders.of(this).get(HistoricoPedidosViewModel::class.java)

        swipeRefreshHistoricoPedidos.setOnRefreshListener {
            doAsync { historicoPedidosViewModel.refresData() }
            true
        }

        historicoPedidosViewModel.getPedidos().observe(this, Observer {pedidos ->
            fragmentHistoricoRecyclerView.layoutManager = LinearLayoutManager(this.context)
            fragmentHistoricoRecyclerView.adapter = HistoricoAdapter(pedidos)
        })

        setHasOptionsMenu(true)



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.menu_historico_pedido, menu)

        var menuItem: MenuItem = menu!!.findItem(R.id.menu_historico_search)
        var searchView: SearchView = menuItem.actionView as SearchView
        searchView.queryHint = getString(R.string.digite_aqui_para_pesquisar)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.e("RCA", newText)

                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_historico_legendas -> {

                val myBuilder = this.context?.let { AlertDialog.Builder(it) }
                val customLayout: View = layoutInflater.inflate(R.layout.legendas_layout, null)
                myBuilder?.setView(customLayout)
                myBuilder
                    ?.setTitle(R.string.legendas)

                    ?.setPositiveButton(R.string.fechar) { _, _ ->

                    }


                val theDialog = myBuilder?.create()
                theDialog?.show()


            }
        }
        return super.onOptionsItemSelected(item)
    }
}