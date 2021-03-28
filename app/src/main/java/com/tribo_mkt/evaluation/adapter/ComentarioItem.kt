package com.tribo_mkt.evaluation.adapter

import android.view.View
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.databinding.CommentViewBinding
import com.tribo_mkt.evaluation.model.ComentarioResposta
import com.xwray.groupie.viewbinding.BindableItem

class ComentarioItem constructor(
    private val comentarioResposta: ComentarioResposta
) : BindableItem<CommentViewBinding>() {

    override fun getLayout(): Int = R.layout.comment_view

    override fun initializeViewBinding(view: View): CommentViewBinding =
        CommentViewBinding.bind(view)

    override fun bind(viewBinding: CommentViewBinding, position: Int) {
        viewBinding.titulo.text = comentarioResposta.nome
        viewBinding.comentario.text = comentarioResposta.conteudo
    }
}