package kr.loner.willog.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class PhotoResponse(
    @SerialName("id")
    val id: String,
    @SerialName("urls")
    val urls: UrlsResponse,
    @SerialName("width")
    val width:Int,
    @SerialName("height")
    val height:Int,


    @SerialName("created_at")
    val createdAt: String,
    @SerialName("user")
    val user: UserResponse
) {
    @Serializable
    internal data class UrlsResponse(
        @SerialName("full")
        val full: String,
        @SerialName("thumb")
        val thumb: String,
    )
}
