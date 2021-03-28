package com.tribo_mkt.evaluation.respostas

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostagemResposta(
    @SerializedName("userId") val usuarioId: String,
    @SerializedName("id") val id: String,
    @SerializedName("title") val titulo: String,
    @SerializedName("body") val conteudo: String,
    var comentarios: Int? = null
) : Parcelable