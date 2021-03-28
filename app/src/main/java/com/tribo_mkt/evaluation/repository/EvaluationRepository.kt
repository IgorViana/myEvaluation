package com.tribo_mkt.evaluation.repository

import com.tribo_mkt.evaluation.model.AlbumResposta
import com.tribo_mkt.evaluation.model.UsuarioResposta
import com.tribo_mkt.evaluation.network.services.EvaluationService
import com.tribo_mkt.evaluation.model.FotoResposta
import com.tribo_mkt.evaluation.respostas.ComentarioResposta
import com.tribo_mkt.evaluation.respostas.PostagemResposta

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

    override suspend fun obterPostagemUsuario(usuarioId: String): List<PostagemResposta> {
        return evaluationService.obterPostagemUsuario(usuarioId)
    }

    override suspend fun obterComentarioUsuario(usuarioId: String): List<ComentarioResposta> {
        return evaluationService.obterComentarioUsuario(usuarioId)
    }

    override suspend fun obterComentarioPostagem(postId: String): List<ComentarioResposta> {
        return evaluationService.obterComentarioPostagem(postId)
    }
}