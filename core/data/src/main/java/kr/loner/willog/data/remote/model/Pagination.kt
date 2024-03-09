package kr.loner.willog.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination<out T>(
    @SerialName("total")
    val total: Long,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("results")
    val results: List<T>
)