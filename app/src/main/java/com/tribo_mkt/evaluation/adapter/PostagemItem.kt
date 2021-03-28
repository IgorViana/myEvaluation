package com.tribo_mkt.evaluation.adapter

import android.view.View
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.databinding.PostViewBinding
import com.tribo_mkt.evaluation.model.PostagemResposta
import com.xwray.groupie.viewbinding.BindableItem

class PostagemItem constructor(
    private val postagemResposta: PostagemResposta,
    private val postagemClickListener: PostagemClickListener

) : BindableItem<PostViewBinding>() {

    override fun getLayout(): Int = R.layout.post_view

    override fun initializeViewBinding(view: View): PostViewBinding = PostViewBinding.bind(view)

    override fun bind(binding: PostViewBinding, position: Int) {
        binding.titulo.text = postagemResposta.titulo
        binding.fundo.setOnClickListener {
            postagemClickListener.onContentClick(postagemResposta)
        }
        binding.comentarios.text =
            "Número de comentários: ${postagemResposta.comentarios.toString()}"
        if (postagemResposta.comentarios == null) {
            binding.comentarios.visibility = View.GONE
        }
    }

    class PostagemClickListener(
        val contentClick: (postagemResposta: PostagemResposta) -> Unit
    ) {
        fun onContentClick(postagemResposta: PostagemResposta) {
            contentClick(postagemResposta)
        }
    }
}
