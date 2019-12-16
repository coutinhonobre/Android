package com.github.maxapp.presentation.clientes.ui.dados

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.github.maxapp.R
import com.github.maxapp.model.Contato
import kotlinx.android.synthetic.main.contatos_item.view.*

class DadosClienteAdapter(var contatos: List<Contato>): RecyclerView.Adapter<DadosClienteAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(contato: Contato){
            contato.let {
                itemView.apply {
                    txtContatosItemNome.text = contato.nome
                    txtContatosItemTelefone.text = contato.telefone
                    txtContatosItemCelular.text = contato.celular
                    txtContatosItemTipo.text = contato.tipo
                    txtContatosItemHobbie.text = ""
                    txtContatosItemEmail.text = ""
                    txtContatosItemDataNascimento.text = contato.eMail
                    txtContatosItemDataNascConjuge.text = contato.dataNascimentoConjuge
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.contatos_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = contatos.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(contatos[position])

    }


}