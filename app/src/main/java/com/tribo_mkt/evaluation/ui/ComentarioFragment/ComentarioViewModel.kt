package com.tribo_mkt.evaluation.ui.ComentarioFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribo_mkt.evaluation.repository.EvaluationRepository
import com.tribo_mkt.evaluation.model.ComentarioResposta
import com.tribo_mkt.evaluation.utils.NetworkState
import kotlinx.coroutines.launch

class ComentarioViewModel(
    private val evaluationRepository: EvaluationRepository
) : ViewModel() {

    private val _comentarioResposta = MutableLiveData<List<ComentarioResposta>>()
    val comentarioResposta: LiveData<List<ComentarioResposta>>
        get() = _comentarioResposta

    private val _comentarioRespostaStatus = MutableLiveData<NetworkState>(NetworkState.INACTIVE)
    val comentarioRespostaStatus: LiveData<NetworkState>
        get() = _comentarioRespostaStatus


    fun getComentarioUsuario(postId: String) {
        _comentarioRespostaStatus.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = evaluationRepository.obterComentarioPostagem(postId)
                _comentarioRespostaStatus.value = NetworkState.LOADED
                _comentarioResposta.postValue(result)
            } catch (ex: Exception) {
                _comentarioRespostaStatus.value = NetworkState.ERROR
            }
        }
    }
}