package com.tribo_mkt.evaluation.ui.PostagemFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tribo_mkt.evaluation.adapter.PostagemAdapter
import com.tribo_mkt.evaluation.adapter.PostagemClickListener
import com.tribo_mkt.evaluation.databinding.PostagensFragmentBinding
import com.tribo_mkt.evaluation.model.UsuarioResposta
import com.tribo_mkt.evaluation.utils.CarregamentoStatusUtils
import org.koin.android.viewmodel.ext.android.viewModel

class PostagensFragment : Fragment() {

    lateinit var binding: PostagensFragmentBinding
    lateinit var postagemAdapter: PostagemAdapter


    private val postagensViewModel: PostagensViewModel by viewModel()
    private val args: PostagensFragmentArgs by navArgs()

    private lateinit var usuarioResposta: UsuarioResposta

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostagensFragmentBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.title =
            "Postagens de ${usuarioResposta.usuarioNome}"
    }

    private fun setUp() {
        usuarioResposta = args.usuario

        configurarAdapter()
        configurarLista()
        observarChamadas()

    }

    private fun observarChamadas() {
        postagensViewModel.getPostagemUsuario(usuarioResposta.id)
        postagensViewModel.postagemResposta.observe(viewLifecycleOwner, Observer {
            postagemAdapter.submitList(it)
        })

        postagensViewModel.postagemRespostaStatus.observe(viewLifecycleOwner, Observer {
            CarregamentoStatusUtils(binding.loading, it).controlarVisibilidade()
        })
    }

    private fun configurarLista() {
        binding.lista.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = postagemAdapter
        }
    }

    private fun configurarAdapter() {
        postagemAdapter = PostagemAdapter(PostagemClickListener {
            val action = PostagensFragmentDirections.actionPostagensFragmentToComentarioFragment(
                it,
                usuarioResposta.usuarioNome
            )
            navController.navigate(action)
        })
    }

}