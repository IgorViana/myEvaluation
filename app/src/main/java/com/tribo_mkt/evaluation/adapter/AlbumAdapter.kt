package com.tribo_mkt.evaluation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tribo_mkt.evaluation.databinding.AlbumViewBinding
import com.tribo_mkt.evaluation.model.AlbumResposta

class AlbumAdapter(private val clickListener: AlbumClickListener) :
    ListAdapter<AlbumResposta, AlbumAdapter.AlbumViewHolder>(AlbumDiffCallback()) {

    class AlbumViewHolder(private val binding: AlbumViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(albumResposta: AlbumResposta, clickListener: AlbumClickListener) {
            binding.album.text = albumResposta.titulo
            binding.fundo.setOnClickListener {
                clickListener.onContentClick(albumResposta)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemShow = AlbumViewBinding.inflate(layoutInflater, parent, false)
        return AlbumViewHolder(itemShow)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album, clickListener)
    }
}

class AlbumDiffCallback : DiffUtil.ItemCallback<AlbumResposta>() {
    override fun areItemsTheSame(oldItem: AlbumResposta, newItem: AlbumResposta): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlbumResposta, newItem: AlbumResposta): Boolean {
        return oldItem == newItem
    }
}

class AlbumClickListener(
    val contentClick: (albumResposta: AlbumResposta) -> Unit
) {
    fun onContentClick(albumResposta: AlbumResposta) {
        contentClick(albumResposta)
    }
}