package kr.loner.willog.data.mapper


import kr.loner.willog.data.remote.model.PhotoResponse
import kr.loner.willog.model.Photo

internal fun PhotoResponse.toModel() = Photo(
    id = id,
    urls = urls.toModel(),
    user = user.toModel()
)

internal fun PhotoResponse.UrlsResponse.toModel() = Photo.Urls(
    full = full,
    thumb = thumb
)

