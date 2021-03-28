package com.tribo_mkt.evaluation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tribo_mkt.evaluation.databinding.CommentViewBinding
import com.tribo_mkt.evaluation.respostas.ComentarioResposta

class ComentarioAdapter() :
    ListAdapter<ComentarioResposta, ComentarioAdapter.ComentarioViewHolder>(ComentarioDiffCallback()) {

    class ComentarioViewHolder(private val binding: CommentViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(comentarioResposta: ComentarioResposta) {
            binding.titulo.text = comentarioResposta.nome
            binding.comentario.text = comentarioResposta.conteudo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemShow = CommentViewBinding.inflate(layoutInflater, parent, false)
        return ComentarioViewHolder(itemShow)
    }

    override fun onBindViewHolder(holder: ComentarioViewHolder, position: Int) {
        val comentario = getItem(position)
        holder.bind(comentario)
    }
}

class ComentarioDiffCallback : DiffUtil.ItemCallback<ComentarioResposta>() {
    override fun areItemsTheSame(
        oldItem: ComentarioResposta,
        newItem: ComentarioResposta
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ComentarioResposta,
        newItem: ComentarioResposta
    ): Boolean {
        return oldItem == newItem
    }
}