package com.tribo_mkt.evaluation.repository

import com.tribo_mkt.evaluation.model.UsuarioResposta
import com.tribo_mkt.evaluation.network.services.EvaluationService

class UsuarioRepository(
    private val evaluationService: EvaluationService
) : UsuarioRepositoryInterface {

    override suspend fun obterUsuariosOrdemAlfabetica(): List<UsuarioResposta> {
        return evaluationService.obterUsuarios()
            .sortedWith(Comparator { s1, s2 -> s1.nome.compareTo(s2.nome) })
    }
}