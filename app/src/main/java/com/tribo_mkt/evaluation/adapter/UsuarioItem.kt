package com.tribo_mkt.evaluation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.databinding.UsuarioViewBinding
import com.tribo_mkt.evaluation.model.UsuarioResposta
import com.xwray.groupie.viewbinding.BindableItem

class UsuarioItem constructor(
    private val usuarioResposta: UsuarioResposta,
    private val usuarioClickListener: UsuarioClickListener
) : BindableItem<UsuarioViewBinding>() {

    override fun getLayout(): Int = R.layout.usuario_view

    override fun initializeViewBinding(view: View): UsuarioViewBinding =
        UsuarioViewBinding.bind(view)

    override fun bind(binding: UsuarioViewBinding, position: Int) {
        binding.nome.text = usuarioResposta.nome
        binding.usuarioNome.text = usuarioResposta.usuarioNome
        binding.telefone.text = usuarioResposta.telefone
        binding.email.text = usuarioResposta.email
        binding.letra.text = usuarioResposta.nome.substring(0, 2).toUpperCase()
        if ((position - 1) % 2 == 0) {
            binding.fundo.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.fundo
                )
            )
        }
        binding.albunsBotao.setOnClickListener {
            usuarioClickListener.onAlbunsClick(usuarioResposta)
        }
        binding.postagensBotao.setOnClickListener {
            usuarioClickListener.onPostagemClick(usuarioResposta)
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
}