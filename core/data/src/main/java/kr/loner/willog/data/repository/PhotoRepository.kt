package kr.loner.willog.data.repository


import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.loner.willog.model.Photo

interface PhotoRepository {
    suspend fun getPhoto(id: String): Photo
    suspend fun searchPhotos(query: String): Flow<PagingData<Photo>>
    fun getBookmarkPhotos(): Flow<List<Photo>>

    suspend fun bookmarkToggle(bookmarkedPhoto: Photo)
}