package kr.loner.willog.data.mapper

import kotlinx.datetime.LocalDateTime
import kr.loner.willog.data.local.entity.BookmarkedPhotoEntity
import kr.loner.willog.model.Photo
import kr.loner.willog.model.User
import java.time.OffsetDateTime

internal fun BookmarkedPhotoEntity.toModel() = Photo(
    id = id,
    urls = Photo.Urls(full = "",thumb = thumb),
    user = User("",""),
    width = 0,
    height = 0,
    createdAt = "",
    isBookmark = true,
)

internal fun Photo.toDto() = BookmarkedPhotoEntity(
    id = id,
    thumb = urls.thumb
)