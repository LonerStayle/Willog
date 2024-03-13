package kr.loner.willog.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kr.loner.willog.data.local.dao.BookmarkedPhotoDao
import kr.loner.willog.data.local.entity.BookmarkedPhotoEntity
import kr.loner.willog.data.mapper.toDto
import kr.loner.willog.data.mapper.toModel
import kr.loner.willog.data.paging.PhotoPagingSource
import kr.loner.willog.data.remote.api.PhotoApi
import kr.loner.willog.model.Photo
import javax.inject.Inject

internal class PhotoRepositoryImpl @Inject constructor(
    private val photoApi: PhotoApi,
    private val bookmarkedPhotoDao: BookmarkedPhotoDao,
) : PhotoRepository {

    override suspend fun bookmarkToggle(bookmarkedPhoto: Photo) {
        if (bookmarkedPhotoDao.getBookmarkedPhoto(bookmarkedPhoto.id) != null) {
            bookmarkedPhotoDao.deleteById(bookmarkedPhoto.id)
        } else {
            bookmarkedPhotoDao.insert(bookmarkedPhoto.toDto())
        }
    }

    override suspend fun getPhoto(id: String): Photo {
        val bookmarkedPhotos = bookmarkedPhotoDao.getBookmarkedPhotos().first()
        return photoApi.getPhoto(id).toModel(
            bookmarkedPhotos.find { it.id == id } != null
        )
    }

    override suspend fun searchPhotos(query: String): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = PhotoPagingSource.DEFAULT_PER_PAGE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                PhotoPagingSource(
                    photoApi = photoApi,
                    query = query,
                )
            }
        ).flow.map { paging ->
            val bookmarkedPhotos = getBookmarkPhotos().first()
            paging.map { photo ->
                photo.toModel(
                    isBookmark = bookmarkedPhotos.find { bookmark ->
                        photo.id == bookmark.id
                    } != null
                )
            }
        }
    }

    override fun getBookmarkPhotos(): Flow<List<Photo>> {
        return bookmarkedPhotoDao.getBookmarkedPhotos().map { list ->
            list.map(BookmarkedPhotoEntity::toModel)
        }
    }
}