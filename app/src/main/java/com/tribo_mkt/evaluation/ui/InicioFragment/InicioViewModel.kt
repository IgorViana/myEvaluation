package com.tribo_mkt.evaluation.ui.InicioFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribo_mkt.evaluation.model.UsuarioResposta
import com.tribo_mkt.evaluation.repository.UsuarioRepository
import kotlinx.coroutines.launch

class InicioViewModel(
    private val usuarioRepository: UsuarioRepository
) : ViewModel() {

    private val _usuarioResposta = MutableLiveData<List<UsuarioResposta>>()
    val usuarioResposta: LiveData<List<UsuarioResposta>>
        get() = _usuarioResposta


    fun getUsuariosOrdemAlfabetica() {
        viewModelScope.launch {
            try {
                val result = usuarioRepository.obterUsuariosOrdemAlfabetica()
                _usuarioResposta.postValue(result)
            } catch (ex: Exception) {
                Log.i("INFO", ex.toString())
            }
        }
    }
}