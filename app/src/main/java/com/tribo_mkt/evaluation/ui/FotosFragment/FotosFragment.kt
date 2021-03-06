package com.tribo_mkt.evaluation.ui.FotosFragment

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
import com.tribo_mkt.evaluation.adapter.FotosItem
import com.tribo_mkt.evaluation.databinding.FotosFragmentBinding
import com.tribo_mkt.evaluation.model.AlbumResposta
import com.tribo_mkt.evaluation.utils.CarregamentoStatusUtils
import com.xwray.groupie.GroupieAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class FotosFragment : Fragment() {

    lateinit var binding: FotosFragmentBinding
    private lateinit var fotosAdapter: GroupieAdapter


    private val fotosViewModel: FotosViewModel by viewModel()
    private val args: FotosFragmentArgs by navArgs()

    private lateinit var album: AlbumResposta
    private lateinit var nomeUsuario: String

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FotosFragmentBinding.inflate(inflater, container, false)
        setUp()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar!!.title = "Fotos de $nomeUsuario"
        super.onActivityCreated(savedInstanceState)
    }

    private fun setUp() {
        album = args.album
        nomeUsuario = args.usuarioNome
        fotosAdapter = GroupieAdapter()
        configurandoFotosLista()
        observandoChamadas()
    }

    private fun observandoChamadas() {
        fotosViewModel.getfotosUsuario(albumId = album.id)
        fotosViewModel.fotosResposta.observe(viewLifecycleOwner, Observer { fotos ->
            fotosAdapter.addAll(fotos.map { foto ->
                FotosItem(foto, configurarClickListener())
            })
        })

        fotosViewModel.fotosRespostaStatus.observe(viewLifecycleOwner, Observer {
            CarregamentoStatusUtils(binding.loading, it).controlarVisibilidade()
        })
    }

    private fun configurarClickListener(): FotosItem.FotosClickListener {
        return FotosItem.FotosClickListener {
            val action = FotosFragmentDirections.actionFotosFragmentToFotoDetalheFragment(it)
            navController.navigate(action)
        }
    }

    private fun configurandoFotosLista() {
        binding.lista.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = fotosAdapter
        }
    }
}