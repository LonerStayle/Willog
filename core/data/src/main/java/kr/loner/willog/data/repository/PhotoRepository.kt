package kr.loner.willog.data.repository


import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.loner.willog.model.BookmarkedPhoto
import kr.loner.willog.model.Photo

interface PhotoRepository {
    suspend fun getPhoto(id: String): Photo
    suspend fun searchPhotos(query: String): Flow<PagingData<Photo>>
    fun getBookmarkPhotos(): Flow<List<BookmarkedPhoto>>

    suspend fun bookmarkToggle(bookmarkedPhoto: BookmarkedPhoto)
}