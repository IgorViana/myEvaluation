package com.tribo_mkt.evaluation.ui.InicioFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribo_mkt.evaluation.model.UsuarioResposta
import com.tribo_mkt.evaluation.repository.EvaluationRepository
import com.tribo_mkt.evaluation.utils.NetworkState
import kotlinx.coroutines.launch

class InicioViewModel(
    private val usuarioRepository: EvaluationRepository
) : ViewModel() {

    private val _usuarioResposta = MutableLiveData<List<UsuarioResposta>>()
    val usuarioResposta: LiveData<List<UsuarioResposta>>
        get() = _usuarioResposta

    private val _usuarioRespostaStatus = MutableLiveData<NetworkState>(NetworkState.INACTIVE)
    val usuarioRespostaStatus: LiveData<NetworkState>
        get() = _usuarioRespostaStatus


    fun getUsuariosOrdemAlfabetica() {
        _usuarioRespostaStatus.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = usuarioRepository.obterUsuariosOrdemAlfabetica()
                _usuarioRespostaStatus.value = NetworkState.LOADED
                _usuarioResposta.postValue(result)
            } catch (ex: Exception) {
                _usuarioRespostaStatus.value = NetworkState.ERROR
                Log.i("INFO", ex.toString())
            }
        }
    }
}