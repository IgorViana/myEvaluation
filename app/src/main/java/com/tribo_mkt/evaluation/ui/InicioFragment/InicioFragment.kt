package com.tribo_mkt.evaluation.ui.InicioFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tribo_mkt.evaluation.PostagensActivity
import com.tribo_mkt.evaluation.adapter.UsuarioAdapter
import com.tribo_mkt.evaluation.adapter.UsuarioClickListener
import com.tribo_mkt.evaluation.databinding.InicioFragmentBinding
import com.tribo_mkt.evaluation.utils.NetworkState
import org.koin.android.viewmodel.ext.android.viewModel

class InicioFragment : Fragment() {
    lateinit var binding: InicioFragmentBinding
    lateinit var usuarioAdapter: UsuarioAdapter

    private val inicioViewModel: InicioViewModel by viewModel()

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = InicioFragmentBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    private fun setUp() {
        configurarAdapter()
        configurarListeners()
    }

    private fun configurarListeners() {
        buscarUsuarios()
        configurarStatusBuscaUsuario()
    }

    private fun configurarStatusBuscaUsuario() {
        inicioViewModel.usuarioRespostaStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                NetworkState.LOADING -> {
                    binding.loading.visibility = View.VISIBLE
                }
                NetworkState.LOADED -> {
                    binding.loading.visibility = View.GONE
                }
                else -> {
                    binding.loading.visibility = View.GONE
                }
            }
        })
    }

    private fun buscarUsuarios() {
        inicioViewModel.getUsuariosOrdemAlfabetica()
        inicioViewModel.usuarioResposta.observe(viewLifecycleOwner, Observer { usuarios ->
            usuarioAdapter.submitList(usuarios)
        })
    }

    private fun configurarAdapter() {
        usuarioAdapter = UsuarioAdapter(
            UsuarioClickListener(
                albunsClick = {
                    val action = InicioFragmentDirections.actionInicioFragmentToAlbunsFragment(it)
                    navController.navigate(action)
                },
                postagemClick = {
                    val action = InicioFragmentDirections.actionInicioFragmentToPostagensFragment(it)
                    navController.navigate(action)
                })
        )
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        binding.lista.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = usuarioAdapter
        }

    }

}