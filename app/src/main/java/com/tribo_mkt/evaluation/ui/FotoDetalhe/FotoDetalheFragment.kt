package com.tribo_mkt.evaluation.ui.FotoDetalhe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.tribo_mkt.evaluation.databinding.FotoDetalheFragmentBinding
import com.tribo_mkt.evaluation.model.FotoResposta

class FotoDetalheFragment : Fragment() {

    lateinit var binding: FotoDetalheFragmentBinding

    private val args: FotoDetalheFragmentArgs by navArgs()

    private lateinit var fotoSelecionada: FotoResposta

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FotoDetalheFragmentBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    private fun setUp() {
        fotoSelecionada = args.foto
        configurarInformacoes()
    }

    private fun configurarInformacoes() {
        binding.imagemNome.text = fotoSelecionada.titulo
        Picasso.get().load(fotoSelecionada.url).into(binding.imagem)
    }

}