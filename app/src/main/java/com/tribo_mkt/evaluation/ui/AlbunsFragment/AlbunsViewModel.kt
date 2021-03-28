package com.tribo_mkt.evaluation.ui.AlbunsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribo_mkt.evaluation.model.AlbumResposta
import com.tribo_mkt.evaluation.repository.EvaluationRepositoryInterface
import com.tribo_mkt.evaluation.utils.NetworkState
import kotlinx.coroutines.launch

class AlbunsViewModel(
    private val evaluationRepository: EvaluationRepositoryInterface
) : ViewModel() {
    private val _albumResposta = MutableLiveData<List<AlbumResposta>>()
    val albumResposta: LiveData<List<AlbumResposta>>
        get() = _albumResposta

    private val _albumRespostaStatus = MutableLiveData<NetworkState>(NetworkState.INACTIVE)
    val albumRespostaStatus: LiveData<NetworkState>
        get() = _albumRespostaStatus


    fun getAlbumUsuario(usuarioId: String) {
        _albumRespostaStatus.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = evaluationRepository.obterAlbumUsuario(usuarioId)
                _albumRespostaStatus.value = NetworkState.LOADED
                _albumResposta.postValue(result)
            } catch (ex: Exception) {
                _albumRespostaStatus.value = NetworkState.ERROR
            }
        }
    }
}