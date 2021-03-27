package com.tribo_mkt.evaluation.repository

import com.tribo_mkt.evaluation.model.AlbumResposta
import com.tribo_mkt.evaluation.model.UsuarioResposta
import com.tribo_mkt.evaluation.model.FotoResposta

interface UsuarioRepositoryInterface {
    suspend fun obterUsuariosOrdemAlfabetica(): List<UsuarioResposta>

    suspend fun obterAlbumUsuario(usuarioId: String): List<AlbumResposta>

    suspend fun obterFotosAlbum(albumId: String): List<FotoResposta>
}