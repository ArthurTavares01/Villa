package com.condominio.qorder.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.condominio.qorder.R
import com.condominio.qorder.model.Noticia

class NoticiaAdapter(private val noticias: List<Noticia>) : RecyclerView.Adapter<NoticiaAdapter.NoticiaViewHolder>() {

    class NoticiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloTextView: TextView = itemView.findViewById(R.id.text_titulo)
        val dataTextView: TextView = itemView.findViewById(R.id.text_data)
        val conteudoTextView: TextView = itemView.findViewById(R.id.text_conteudo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_noticia, parent, false)
        return NoticiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val noticia = noticias[position]
        holder.tituloTextView.text = noticia.titulo
        holder.dataTextView.text = noticia.data
        holder.conteudoTextView.text = noticia.conteudo
    }

    override fun getItemCount(): Int = noticias.size
}