package com.tribo_mkt.evaluation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tribo_mkt.evaluation.databinding.PostViewBinding
import com.tribo_mkt.evaluation.model.PostagemResposta


class PostagemAdapter(private val clickListener: PostagemClickListener) :
    ListAdapter<PostagemResposta, PostagemAdapter.PostagemViewHolder>(PostagemDiffCallback()) {

    class PostagemViewHolder(private val binding: PostViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(postagemResposta: PostagemResposta, clickListener: PostagemClickListener) {
            binding.titulo.text = postagemResposta.titulo
            binding.fundo.setOnClickListener {
                clickListener.onContentClick(postagemResposta)
                /*val intent = Intent(activity, ComentariosActivity::class.java)
                intent.putExtra("postagemId", items[position].id)
                intent.putExtra("usuarioNome", usuarioNome)
                activity.startActivity(intent)*/
            }
            //binding.root.context.resources.getString(R.string.n_)
            //val yourstring: String = contextgetResources().getString(R.string.yourstring)
            binding.comentarios.text =
                "Número de comentários: ${postagemResposta.comentarios.toString()}"
            if (postagemResposta.comentarios == null) {
                binding.comentarios.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostagemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemShow = PostViewBinding.inflate(layoutInflater, parent, false)
        return PostagemViewHolder(itemShow)
    }

    override fun onBindViewHolder(holder: PostagemViewHolder, position: Int) {
        val postagem = getItem(position)
        holder.bind(postagem, clickListener)
    }
}

class PostagemDiffCallback : DiffUtil.ItemCallback<PostagemResposta>() {
    override fun areItemsTheSame(oldItem: PostagemResposta, newItem: PostagemResposta): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostagemResposta, newItem: PostagemResposta): Boolean {
        return oldItem == newItem
    }
}

class PostagemClickListener(
    val contentClick: (postagemResposta: PostagemResposta) -> Unit
) {
    fun onContentClick(postagemResposta: PostagemResposta) {
        contentClick(postagemResposta)
    }
}