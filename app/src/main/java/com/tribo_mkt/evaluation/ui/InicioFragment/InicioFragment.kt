package com.tribo_mkt.evaluation.ui.InicioFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tribo_mkt.evaluation.adapter.UsuarioItem
import com.tribo_mkt.evaluation.databinding.InicioFragmentBinding
import com.tribo_mkt.evaluation.utils.CarregamentoStatusUtils
import com.xwray.groupie.GroupieAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class InicioFragment : Fragment() {
    lateinit var binding: InicioFragmentBinding
    lateinit var inicioAdapter: GroupieAdapter

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
        inicioAdapter = GroupieAdapter()
        configurarListeners()
        configurarRecyclerView()
    }

    private fun configurarListeners() {
        buscarUsuarios()
        configurarStatusBuscaUsuario()
    }

    private fun configurarStatusBuscaUsuario() {
        inicioViewModel.usuarioRespostaStatus.observe(viewLifecycleOwner, Observer {
            CarregamentoStatusUtils(binding.loading, it).controlarVisibilidade()
        })
    }

    private fun buscarUsuarios() {
        inicioViewModel.getUsuariosOrdemAlfabetica()
        inicioViewModel.usuarioResposta.observe(viewLifecycleOwner, Observer { usuarios ->
            inicioAdapter.addAll(usuarios.map { usuario ->
                UsuarioItem(usuario, configurarClickListener())
            })
        })
    }

    private fun configurarClickListener(): UsuarioItem.UsuarioClickListener {
        return UsuarioItem.UsuarioClickListener(
            albunsClick = {
                val action = InicioFragmentDirections.actionInicioFragmentToAlbunsFragment(it)
                navController.navigate(action)
            },
            postagemClick = {
                val action =
                    InicioFragmentDirections.actionInicioFragmentToPostagensFragment(it)
                navController.navigate(action)
            })
    }

    private fun configurarRecyclerView() {
        binding.lista.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = inicioAdapter
        }
    }

}