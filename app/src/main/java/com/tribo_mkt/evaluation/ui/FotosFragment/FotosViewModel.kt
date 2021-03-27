package com.tribo_mkt.evaluation.ui.FotosFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribo_mkt.evaluation.repository.EvaluationRepository
import com.tribo_mkt.evaluation.model.FotoResposta
import com.tribo_mkt.evaluation.utils.NetworkState
import kotlinx.coroutines.launch

class FotosViewModel(
    private val evaluationRepository: EvaluationRepository
) : ViewModel() {
    private val _fotosResposta = MutableLiveData<List<FotoResposta>>()
    val fotosResposta: LiveData<List<FotoResposta>>
        get() = _fotosResposta

    private val _fotosRespostaStatus = MutableLiveData<NetworkState>(NetworkState.INACTIVE)
    val fotosRespostaStatus: LiveData<NetworkState>
        get() = _fotosRespostaStatus


    fun getfotosUsuario(albumId: String) {
        _fotosRespostaStatus.value = NetworkState.LOADING
        viewModelScope.launch {
            try {
                val result = evaluationRepository.obterFotosAlbum(albumId)
                _fotosRespostaStatus.value = NetworkState.LOADED
                _fotosResposta.postValue(result)
            } catch (ex: Exception) {
                _fotosRespostaStatus.value = NetworkState.ERROR
            }
        }
    }
}