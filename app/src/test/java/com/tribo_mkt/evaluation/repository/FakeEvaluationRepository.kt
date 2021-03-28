package com.tribo_mkt.evaluation.repository

import com.tribo_mkt.evaluation.model.*

class FakeEvaluationRepository : EvaluationRepositoryInterface {
    override suspend fun obterUsuariosOrdemAlfabetica(): List<UsuarioResposta> {
        TODO("Not yet implemented")
    }

    override suspend fun obterAlbumUsuario(usuarioId: String): List<AlbumResposta> {
        return if (usuarioId == "0" || usuarioId.isEmpty()) {
            emptyList()
        } else if (usuarioId == "ERRO") {
            throw Exception()
        } else {
            listOf(
                AlbumResposta(usuarioId, "1", "Titulo 1"),
                AlbumResposta(usuarioId, "2", "Titulo 2"),
                AlbumResposta(usuarioId, "3", "Titulo 3")
            )
        }
    }

    override suspend fun obterFotosAlbum(albumId: String): List<FotoResposta> {
        TODO("Not yet implemented")
    }

    override suspend fun obterPostagemUsuario(usuarioId: String): List<PostagemResposta> {
        TODO("Not yet implemented")
    }

    override suspend fun obterComentarioUsuario(albumId: String): List<ComentarioResposta> {
        TODO("Not yet implemented")
    }

    override suspend fun obterComentarioPostagem(postId: String): List<ComentarioResposta> {
        TODO("Not yet implemented")
    }
}