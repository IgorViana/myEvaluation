package com.tribo_mkt.evaluation.adapter

import android.view.View
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.databinding.AlbumViewBinding
import com.tribo_mkt.evaluation.model.AlbumResposta
import com.xwray.groupie.viewbinding.BindableItem

class AlbumItem constructor(
    private val albumResposta: AlbumResposta,
    private val albumClickListener: AlbumClickListener
) : BindableItem<AlbumViewBinding>() {

    override fun getLayout(): Int = R.layout.album_view

    override fun initializeViewBinding(view: View): AlbumViewBinding = AlbumViewBinding.bind(view)

    override fun bind(viewBinding: AlbumViewBinding, position: Int) {
        viewBinding.album.text = albumResposta.titulo
        viewBinding.fundo.setOnClickListener {
            albumClickListener.onContentClick(albumResposta)
        }
    }

    class AlbumClickListener(
        val contentClick: (albumResposta: AlbumResposta) -> Unit
    ) {
        fun onContentClick(albumResposta: AlbumResposta) {
            contentClick(albumResposta)
        }
    }
}