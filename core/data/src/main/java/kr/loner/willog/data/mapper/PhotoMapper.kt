package kr.loner.willog.data.mapper


import android.util.Log
import kr.loner.willog.data.remote.model.PhotoResponse
import kr.loner.willog.model.Photo
import java.text.SimpleDateFormat
import java.util.Locale

internal fun PhotoResponse.toModel(isBookmark: Boolean = false): Photo {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.KOREAN).parse(createdAt)
    val dateString = runCatching {
        SimpleDateFormat("yyyy/MM/dd hh:mm:ss",Locale.KOREAN).format(date)
    }.getOrElse { "" }

    return Photo(
        id = id,
        width = width,
        height = height,
        createdAt = dateString,
        urls = urls.toModel(),
        user = user.toModel(),
        isBookmark = isBookmark
    )
}

internal fun PhotoResponse.UrlsResponse.toModel() = Photo.Urls(
    full = full, thumb = thumb
)

