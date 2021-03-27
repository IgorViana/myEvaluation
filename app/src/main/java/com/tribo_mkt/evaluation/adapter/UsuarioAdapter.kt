package com.tribo_mkt.evaluation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.databinding.UsuarioViewBinding
import com.tribo_mkt.evaluation.model.UsuarioResposta

class UsuarioAdapter(private val clickListener: UsuarioClickListener) :
    ListAdapter<UsuarioResposta, UsuarioAdapter.UsuarioViewHolder>(UsuarioDiffCallback()) {

    class UsuarioViewHolder(private val binding: UsuarioViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(usuarioResposta: UsuarioResposta, clickListener: UsuarioClickListener) {
            binding.nome.text = usuarioResposta.nome
            binding.usuarioNome.text = usuarioResposta.usuarioNome
            binding.telefone.text = usuarioResposta.telefone
            binding.email.text = usuarioResposta.email
            binding.letra.text = usuarioResposta.nome.substring(0, 2).toUpperCase()
            if ((adapterPosition - 1) % 2 == 0) {
                binding.fundo.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.fundo
                    )
                )
            }
            binding.albunsBotao.setOnClickListener {
                clickListener.onAlbunsClick(usuarioResposta)
            }
            binding.postagensBotao.setOnClickListener {
                clickListener.onPostagemClick(usuarioResposta)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemShow = UsuarioViewBinding.inflate(layoutInflater, parent, false)
        return UsuarioViewHolder(itemShow)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = getItem(position)
        holder.bind(usuario, clickListener)
    }
}

class UsuarioDiffCallback : DiffUtil.ItemCallback<UsuarioResposta>() {
    override fun areItemsTheSame(oldItem: UsuarioResposta, newItem: UsuarioResposta): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UsuarioResposta, newItem: UsuarioResposta): Boolean {
        return oldItem == newItem
    }
}

class UsuarioClickListener(
    val albunsClick: (usuarioResposta: UsuarioResposta) -> Unit,
    val postagemClick: (usuarioResposta: UsuarioResposta) -> Unit
) {
    fun onAlbunsClick(usuarioResposta: UsuarioResposta) {
        albunsClick(usuarioResposta)
    }

    fun onPostagemClick(usuarioResposta: UsuarioResposta) {
        postagemClick(usuarioResposta)
    }
}