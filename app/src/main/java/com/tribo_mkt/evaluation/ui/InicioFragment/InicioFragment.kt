package com.tribo_mkt.evaluation.ui.InicioFragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.tribo_mkt.evaluation.AlbunsActivity
import com.tribo_mkt.evaluation.PostagensActivity
import com.tribo_mkt.evaluation.R
import com.tribo_mkt.evaluation.adapter.UsuarioAdapter
import com.tribo_mkt.evaluation.adapter.UsuarioClickListener
import com.tribo_mkt.evaluation.databinding.InicioFragmentBinding
import com.tribo_mkt.evaluation.model.UsuarioResposta
import org.koin.android.viewmodel.ext.android.viewModel

class InicioFragment : Fragment() {
    lateinit var binding: InicioFragmentBinding

    //private lateinit var viewModel: InicioViewModel
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

        /*val stringRequest = StringRequest(
            Request.Method.GET, "https://jsonplaceholder.typicode.com/users",
            Response.Listener<String> { response ->
                val usuarios = Gson().newBuilder().create()
                    .fromJson(response, Array<UsuarioResposta>::class.java).toMutableList()

                usuarios.sortWith(Comparator { s1, s2 -> s1.nome.compareTo(s2.nome) })

                val lista = findViewById<RecyclerView>(R.id.lista)!!
                val adapter = Adapter(this, usuarios)
                lista.layoutManager = LinearLayoutManager(this)
                lista.adapter = adapter
                findViewById<View>(R.id.loading)!!.visibility = View.GONE
            },
            Response.ErrorListener {
                findViewById<View>(R.id.loading)!!.visibility = View.GONE
                Toast.makeText(
                    this,
                    "Algo errado aconteceu. Tente novamente mais tarde.",
                    Toast.LENGTH_LONG
                ).show()
            })*/

        //Volley.newRequestQueue(this).add(stringRequest)
        return binding.root
    }

    private fun setUp() {
        val adapter = UsuarioAdapter(UsuarioClickListener { resposta ->

        })
        binding.lista.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }

        inicioViewModel.getUsuariosOrdemAlfabetica()

        //binding.loading.visibility = View.VISIBLE
        inicioViewModel.usuarioResposta.observe(viewLifecycleOwner, Observer { usuarios ->
            adapter.submitList(usuarios)
            binding.loading.visibility = View.GONE
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar!!.title = "Usuários"
        super.onActivityCreated(savedInstanceState)
    }

    /*class Adapter(
        val activity: Activity,
        var items: List<UsuarioResposta>
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.usuario_view, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val view = holder as ViewHolder
            view.nome.text = items[position].nome
            view.usuarioNome.text = items[position].usuarioNome
            view.telefone.text = items[position].telefone
            view.email.text = items[position].email
            view.letra.text = items[position].nome.substring(0, 2).toUpperCase()
            if ((position - 1) % 2 == 0) {
                view.fundo.setBackgroundColor(ContextCompat.getColor(activity, R.color.fundo))
            }
            view.albunsBotao.setOnClickListener {
                val intent = Intent(activity, AlbunsActivity::class.java)
                intent.putExtra("usuarioId", items[position].id)
                intent.putExtra("usuarioNome", items[position].usuarioNome)
                activity.startActivity(intent)
            }
            view.postagensBotao.setOnClickListener {
                val intent = Intent(activity, PostagensActivity::class.java)
                intent.putExtra("usuarioId", items[position].id)
                intent.putExtra("usuarioNome", items[position].usuarioNome)
                activity.startActivity(intent)
            }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val nome = itemView.findViewById<TextView>(R.id.nome)!!
            val usuarioNome = itemView.findViewById<TextView>(R.id.usuarioNome)!!
            val telefone = itemView.findViewById<TextView>(R.id.telefone)!!
            val email = itemView.findViewById<TextView>(R.id.email)!!
            val fundo = itemView.findViewById<View>(R.id.fundo)!!
            val letra = itemView.findViewById<TextView>(R.id.letra)!!
            val albunsBotao = itemView.findViewById<TextView>(R.id.albunsBotao)!!
            val postagensBotao = itemView.findViewById<TextView>(R.id.postagensBotao)!!
        }
    }*/

}