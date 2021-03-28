package com.tribo_mkt.evaluation.ui.AlbunsFragment

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
import com.tribo_mkt.evaluation.adapter.AlbumItem
import com.tribo_mkt.evaluation.databinding.AlbunsFragmentBinding
import com.tribo_mkt.evaluation.model.UsuarioResposta
import com.tribo_mkt.evaluation.utils.CarregamentoStatusUtils
import com.xwray.groupie.GroupieAdapter
import org.koin.android.viewmodel.ext.android.viewModel


class AlbunsFragment : Fragment() {

    lateinit var binding: AlbunsFragmentBinding
    lateinit var albumAdapter: GroupieAdapter

    private val albunsViewModel: AlbunsViewModel by viewModel()

    private val args: AlbunsFragmentArgs by navArgs()

    private val navController: NavController by lazy {
        findNavController()
    }

    private lateinit var usuario: UsuarioResposta

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AlbunsFragmentBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.title =
            "Albuns de ${usuario.usuarioNome}"
    }

    private fun setUp() {
        usuario = args.usuario

        albumAdapter = GroupieAdapter()
        configurarLista()
        observarChamadas()
    }

    private fun observarChamadas() {
        albunsViewModel.getAlbumUsuario(usuarioId = usuario.id)
        albunsViewModel.albumResposta.observe(viewLifecycleOwner, Observer {
            albumAdapter.addAll(it.map { albuns ->
                AlbumItem(albuns, configurarClickListener())
            })
        })

        albunsViewModel.albumRespostaStatus.observe(viewLifecycleOwner, Observer {
            CarregamentoStatusUtils(binding.loading, it).controlarVisibilidade()
        })
    }

    private fun configurarLista() {
        binding.lista.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = albumAdapter
        }
    }

    private fun configurarClickListener(): AlbumItem.AlbumClickListener {
        return AlbumItem.AlbumClickListener {
            val action =
                AlbunsFragmentDirections.actionAlbunsFragmentToFotosFragment(
                    it,
                    usuario.usuarioNome
                )
            navController.navigate(action)
        }
    }

}