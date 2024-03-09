package kr.loner.willog.data.mapper

import kr.loner.willog.data.local.entity.BookmarkedPhotoEntity
import kr.loner.willog.model.BookmarkedPhoto

internal fun BookmarkedPhotoEntity.toModel() = BookmarkedPhoto(
    id = id,
    thumb = thumb
)

internal fun BookmarkedPhoto.toDto() = BookmarkedPhotoEntity(
    id = id,
    thumb = thumb
)