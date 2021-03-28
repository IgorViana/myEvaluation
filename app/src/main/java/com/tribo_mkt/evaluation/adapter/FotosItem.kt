package com.tribo_mkt.evaluation.adapter

import android.view.View
import com.squareup.picasso.Picasso
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.databinding.PhotoViewBinding
import com.tribo_mkt.evaluation.model.FotoResposta
import com.xwray.groupie.viewbinding.BindableItem

class FotosItem constructor(
    private val fotoResposta: FotoResposta,
    private val fotosClickListener: FotosClickListener
) : BindableItem<PhotoViewBinding>() {

    override fun getLayout(): Int = R.layout.photo_view

    override fun initializeViewBinding(view: View): PhotoViewBinding = PhotoViewBinding.bind(view)

    override fun bind(viewBinding: PhotoViewBinding, position: Int) {
        viewBinding.titulo.text = fotoResposta.titulo
        viewBinding.fundo.setOnClickListener {
            fotosClickListener.onContentClick(fotoResposta)
        }
        Picasso.get().load(fotoResposta.thumbnailUrl).into(viewBinding.thumb)
    }

    class FotosClickListener(
        val contentClick: (fotoResposta: FotoResposta) -> Unit
    ) {
        fun onContentClick(fotoResposta: FotoResposta) {
            contentClick(fotoResposta)
        }
    }
}