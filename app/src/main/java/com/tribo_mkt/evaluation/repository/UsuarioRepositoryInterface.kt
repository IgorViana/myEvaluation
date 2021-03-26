package com.tribo_mkt.evaluation.repository

import com.tribo_mkt.evaluation.model.UsuarioResposta

interface UsuarioRepositoryInterface {
    suspend fun obterUsuariosOrdemAlfabetica(): List<UsuarioResposta>
}