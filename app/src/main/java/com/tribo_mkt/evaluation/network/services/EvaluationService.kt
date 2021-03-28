package com.tribo_mkt.evaluation.network.services

import com.tribo_mkt.evaluation.model.AlbumResposta
import com.tribo_mkt.evaluation.model.UsuarioResposta
import com.tribo_mkt.evaluation.model.ComentarioResposta
import com.tribo_mkt.evaluation.model.FotoResposta
import com.tribo_mkt.evaluation.model.PostagemResposta
import retrofit2.http.GET
import retrofit2.http.Query

interface EvaluationService {
    @GET("users")
    suspend fun obterUsuarios(): List<UsuarioResposta>

    @GET("albums")
    suspend fun obterAlbumUsuario(@Query("userId") usuarioId: String): List<AlbumResposta>

    @GET("photos")
    suspend fun obterFotosAlbum(@Query("albumId") albumId: String): List<FotoResposta>

    @GET("posts")
    suspend fun obterPostagemUsuario(@Query("userId") usuarioId: String): List<PostagemResposta>

    @GET("comments")
    suspend fun obterComentarioUsuario(@Query("userId") usuarioId: String): List<ComentarioResposta>

    @GET("comments")
    suspend fun obterComentarioPostagem(@Query("postId") postId: String): List<ComentarioResposta>

}