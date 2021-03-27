package com.tribo_mkt.evaluation.repository

import com.tribo_mkt.evaluation.model.AlbumResposta
import com.tribo_mkt.evaluation.model.UsuarioResposta
import com.tribo_mkt.evaluation.network.services.EvaluationService
import com.tribo_mkt.evaluation.model.FotoResposta

class EvaluationRepository(
    private val evaluationService: EvaluationService
) : UsuarioRepositoryInterface {

    override suspend fun obterUsuariosOrdemAlfabetica(): List<UsuarioResposta> {
        return evaluationService.obterUsuarios()
            .sortedWith(Comparator { s1, s2 -> s1.nome.compareTo(s2.nome) })
    }

    override suspend fun obterAlbumUsuario(usuarioId: String): List<AlbumResposta> {
        return evaluationService.obterAlbumUsuario(usuarioId)
    }

    override suspend fun obterFotosAlbum(albumId: String): List<FotoResposta> {
        return evaluationService.obterFotosAlbum(albumId)
    }
}