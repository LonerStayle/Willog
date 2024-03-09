package kr.loner.willog.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookmarkedPhotoEntity(
    @PrimaryKey
    val id: String,
    val thumb: String,
)
