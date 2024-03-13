package kr.loner.willog.model


data class Photo(
    val id: String,
    val width: Int,
    val height: Int,
    val createdAt: String,
    val urls: Urls,
    val user: User,
    val isBookmark: Boolean = false
) {
    data class Urls(
        val full: String,
        val thumb: String,
    )
}
