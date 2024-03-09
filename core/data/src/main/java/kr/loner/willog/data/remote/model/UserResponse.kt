package kr.loner.willog.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UserResponse(
    @SerialName("id")
    val id: String,
    @SerialName("username")
    val username: String,
)
