package com.tribo_mkt.evaluation.repository

import com.tribo_mkt.evaluation.model.AlbumResposta
import com.tribo_mkt.evaluation.model.UsuarioResposta
import com.tribo_mkt.evaluation.model.FotoResposta
import com.tribo_mkt.evaluation.model.ComentarioResposta
import com.tribo_mkt.evaluation.model.PostagemResposta

interface EvaluationRepositoryInterface {
    suspend fun obterUsuariosOrdemAlfabetica(): List<UsuarioResposta>

    suspend fun obterAlbumUsuario(usuarioId: String): List<AlbumResposta>

    suspend fun obterFotosAlbum(albumId: String): List<FotoResposta>

    suspend fun obterPostagemUsuario(usuarioId: String): List<PostagemResposta>

    suspend fun obterComentarioUsuario(albumId: String): List<ComentarioResposta>

    suspend fun obterComentarioPostagem(postId: String): List<ComentarioResposta>
}