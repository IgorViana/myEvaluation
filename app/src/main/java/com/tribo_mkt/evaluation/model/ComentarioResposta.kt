package com.tribo_mkt.evaluation.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComentarioResposta(
    @SerializedName("postId") val postagemId: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val nome: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val conteudo: String
) : Parcelable