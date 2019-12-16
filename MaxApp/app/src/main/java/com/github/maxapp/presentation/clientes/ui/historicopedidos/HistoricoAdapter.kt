package com.github.maxapp.presentation.clientes.ui.historicopedidos

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.maxapp.R
import com.github.maxapp.model.Pedido
import kotlinx.android.synthetic.main.historico_pedidos_item.view.*


class HistoricoAdapter(var pedidos: List<Pedido>) :
    RecyclerView.Adapter<HistoricoAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(pedido: Pedido) {
            pedido.let {
                itemView.apply {
                    statusPedido(pedido)
                    histPedidosItemNumeroPedidoTxt.text = pedido.numeroPedRca
                    histPedidosItemClienteTxt.text =
                        "${pedido.codigoCliente} - ${pedido.nomeCliente}"
                    histPedidosItemStatusFieldTxt.text = pedido.status
                    criticaPedido(pedido)
                    histPedidosItemHoraTxt.text = "12:00"
                    histPedidosItemValorTxt.text = "R$ 0.00"
                    setarLegendas(pedido)


                }
            }


        }

        private fun View.setarLegendas(pedido: Pedido) {
            pedido.legendas?.let {
                var result: List<String> = it.split(",").map { it.trim() }

                result.forEach {

                    when (it) {
                        "PEDIDO_SOFREU_CORTE" -> setImageLegenda(R.drawable.ic_maxima_legenda_corte)
                        "PEDIDO_COM_FALTA" -> setImageLegenda(R.drawable.ic_maxima_legenda_falta)
                        "PEDIDO_CANCELADO_ERP" -> setImageLegenda(R.drawable.ic_maxima_legenda_cancelamento)
                        "PEDIDO_COM_DEVOLUCAO" -> setImageLegenda(R.drawable.ic_maxima_legenda_devolucao)
                        "PEDIDO_FEITO_TELEMARKETING" -> setImageLegenda(R.drawable.ic_maxima_legenda_telemarketing)
                        else -> setImageLegenda(0)

                    }
                }


            }
        }

        private fun View.criticaPedido(pedido: Pedido) {
            when (pedido.critica) {
                "SUCESSO" -> setImageCritica(R.drawable.ic_maxima_critica_sucesso)
                "FALHA_PARCIAL" -> setImageCritica(R.drawable.ic_maxima_critica_alerta)
                "FALHA_TOTAL" -> setImageCritica(R.drawable.ic_error_black_24dp)
                else -> setImageCritica(R.drawable.ic_maxima_aguardando_critica)
            }
        }

        private fun View.setImageCritica(imagem: Int) {
            histPedidosItemCriticaTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0, 0, imagem, 0
            )
        }

        private fun View.setImageLegenda(imagem: Int) {
            if (imagem != 0) {
                histPedidosItemLegendaTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, 0, imagem, 0
                )
            } else {
                histPedidosItemLegendaTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    0, 0, 0, 0
                )
            }
        }

        private fun View.statusPedido(
            pedido: Pedido
        ) {
            when (pedido.tipo) {
                "ORCAMENTO" -> tipoPedidoView(pedido.tipo, R.color.status_pedido_orcamento)
                "CANCELADO" -> tipoPedidoView(pedido.tipo, R.color.status_pedido_cancelado)
                "FATURADO" -> tipoPedidoView(pedido.tipo, R.color.status_pedido_faturado)
                "MONTADO" -> tipoPedidoView(pedido.tipo, R.color.status_pedido_montado)
                "LIBERADO" -> tipoPedidoView(pedido.tipo, R.color.status_pedido_liberado)
                "BLOQUEADO" -> tipoPedidoView(pedido.tipo, R.color.status_pedido_bloqueado)
                "PENDENTE" -> tipoPedidoView(pedido.tipo, R.color.status_pedido_pendente)
                "PEDIDO" -> tipoPedidoView("!", R.color.status_pedido__recusado_erp)
                "FV" -> {
                    histPedidosItemCardViewTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_maxima_em_processamento, 0, 0, 0
                    )
                    histPedidosItemCardView.setCardBackgroundColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.status_pedido_fv
                        )
                    )
                }

            }
        }

        private fun View.tipoPedidoView(tipo: String, color: Int) {
            histPedidosItemCardViewTxt.setCompoundDrawablesRelativeWithIntrinsicBounds(
                0, 0, 0, 0
            )
            histPedidosItemCardViewTxt.text = tipo[0].toString()
            histPedidosItemCardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    color
                )
            )

        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.historico_pedidos_item, parent, false)
        return HistoricoAdapter.MyViewHolder(itemView)
    }

    override fun getItemCount() = pedidos.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(pedidos[position])
    }

}



