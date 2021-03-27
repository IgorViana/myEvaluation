package com.tribo_mkt.evaluation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tribo_mkt.evaluation.databinding.PhotoViewBinding
import com.tribo_mkt.evaluation.model.FotoResposta

class FotosAdapter(private val clickListener: FotosClickListener) :
    ListAdapter<FotoResposta, FotosAdapter.FotosViewHolder>(FotosDiffCallback()) {

    class FotosViewHolder(private val binding: PhotoViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fotoResposta: FotoResposta, clickListener: FotosClickListener) {
            binding.titulo.text = fotoResposta.titulo
            binding.fundo.setOnClickListener {
                clickListener.onContentClick(fotoResposta)
            }
            Picasso.get().load(fotoResposta.thumbnailUrl).into(binding.thumb)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemShow = PhotoViewBinding.inflate(layoutInflater, parent, false)
        return FotosViewHolder(itemShow)
    }

    override fun onBindViewHolder(holder: FotosViewHolder, position: Int) {
        val fotos = getItem(position)
        holder.bind(fotos, clickListener)
    }
}

class FotosDiffCallback : DiffUtil.ItemCallback<FotoResposta>() {
    override fun areItemsTheSame(oldItem: FotoResposta, newItem: FotoResposta): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FotoResposta, newItem: FotoResposta): Boolean {
        return oldItem == newItem
    }
}

class FotosClickListener(
    val contentClick: (fotoResposta: FotoResposta) -> Unit
) {
    fun onContentClick(fotoResposta: FotoResposta) {
        contentClick(fotoResposta)
    }
}