package com.tribo_mkt.evaluation.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FotoResposta(
    @SerializedName("albumId") val albumId: String,
    @SerializedName("id") val id: String,
    @SerializedName("title") val titulo: String,
    @SerializedName("url") val url: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
) : Parcelable