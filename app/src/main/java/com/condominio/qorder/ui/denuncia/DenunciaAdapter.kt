package com.condominio.qorder.ui.denuncia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.condominio.qorder.R
import com.condominio.qorder.model.Denuncia

class DenunciaAdapter(private val denuncias: List<Denuncia>) : RecyclerView.Adapter<DenunciaAdapter.DenunciaViewHolder>() {

    class DenunciaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloTextView: TextView = itemView.findViewById(R.id.text_titulo)
        val dataTextView: TextView = itemView.findViewById(R.id.text_data)
        val descricaoTextView: TextView = itemView.findViewById(R.id.text_descricao)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DenunciaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_denuncia, parent, false)
        return DenunciaViewHolder(view)
    }

    override fun onBindViewHolder(holder: DenunciaViewHolder, position: Int) {
        val denuncia = denuncias[position]
        holder.tituloTextView.text = denuncia.titulo
        holder.dataTextView.text = denuncia.data
        holder.descricaoTextView.text = denuncia.descricao
    }

    override fun getItemCount(): Int = denuncias.size
}