package com.tribo_mkt.evaluation.ui.PostagemFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribo_mkt.evaluation.repository.EvaluationRepository
import com.tribo_mkt.evaluation.respostas.PostagemResposta
import com.tribo_mkt.evaluation.utils.NetworkState
import kotlinx.coroutines.launch

class PostagensViewModel(
    private val evaluationRepository: EvaluationRepository
) : ViewModel() {

    private val _postagemResposta = MutableLiveData<List<PostagemResposta>>()
    val postagemResposta: LiveData<List<PostagemResposta>>
        get() = _postagemResposta

    private val _postagemRespostaStatus = MutableLiveData<NetworkState>(NetworkState.INACTIVE)
    val postagemRespostaStatus: LiveData<NetworkState>
        get() = _postagemRespostaStatus


    fun getPostagemUsuario(usuarioId: String) {
        _postagemRespostaStatus.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val resultPosts = evaluationRepository.obterPostagemUsuario(usuarioId)
                val resultComments = evaluationRepository.obterComentarioUsuario(usuarioId)

                resultPosts.forEach {
                    it.comentarios =
                        resultComments.filter { comment -> comment.postagemId == it.id }.size
                }

                _postagemRespostaStatus.value = NetworkState.LOADED
                _postagemResposta.postValue(resultPosts)
            } catch (ex: Exception) {
                _postagemRespostaStatus.value = NetworkState.ERROR
            }
        }
    }
}