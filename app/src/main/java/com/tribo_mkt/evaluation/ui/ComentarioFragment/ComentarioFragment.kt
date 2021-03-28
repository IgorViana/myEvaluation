package com.tribo_mkt.evaluation.ui.ComentarioFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tribo_mkt.evaluation.adapter.ComentarioAdapter
import com.tribo_mkt.evaluation.databinding.ComentarioFragmentBinding
import com.tribo_mkt.evaluation.respostas.PostagemResposta
import com.tribo_mkt.evaluation.utils.CarregamentoStatusUtils
import org.koin.android.viewmodel.ext.android.viewModel

class ComentarioFragment : Fragment() {

    lateinit var binding: ComentarioFragmentBinding
    lateinit var comentarioAdapter: ComentarioAdapter


    private val comentarioViewModel: ComentarioViewModel by viewModel()
    private val args: ComentarioFragmentArgs by navArgs()

    private lateinit var postagemResposta: PostagemResposta
    private lateinit var usuarioNome: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ComentarioFragmentBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.title =
            "Comentários de $usuarioNome"
    }

    private fun setUp() {
        postagemResposta = args.postagem
        usuarioNome = args.usuarioNome

        comentarioAdapter = ComentarioAdapter()

        binding.lista.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = comentarioAdapter
        }

        comentarioViewModel.getComentarioUsuario(postagemResposta.id)
        comentarioViewModel.comentarioResposta.observe(viewLifecycleOwner, Observer {
            comentarioAdapter.submitList(it)
        })

        comentarioViewModel.comentarioRespostaStatus.observe(viewLifecycleOwner, Observer {
            CarregamentoStatusUtils(binding.loading, it).controlarVisibilidade()
        })

    }

}