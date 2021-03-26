package com.tribo_mkt.evaluation.network.services

import com.tribo_mkt.evaluation.model.UsuarioResposta
import retrofit2.http.GET

interface EvaluationService {
    @GET("users")
    suspend fun obterUsuarios(): List<UsuarioResposta>
}