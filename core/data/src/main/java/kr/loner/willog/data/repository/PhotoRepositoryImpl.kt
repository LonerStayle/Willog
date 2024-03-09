package kr.loner.willog.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kr.loner.willog.data.local.dao.BookmarkedPhotoDao
import kr.loner.willog.data.local.entity.BookmarkedPhotoEntity
import kr.loner.willog.data.mapper.toDto
import kr.loner.willog.data.mapper.toModel
import kr.loner.willog.data.paging.PhotoPagingSource
import kr.loner.willog.data.remote.api.PhotoApi
import kr.loner.willog.data.remote.model.PhotoResponse
import kr.loner.willog.model.BookmarkedPhoto
import kr.loner.willog.model.Photo
import javax.inject.Inject

internal class PhotoRepositoryImpl @Inject constructor(
    private val photoApi: PhotoApi,
    private val bookmarkedPhotoDao: BookmarkedPhotoDao,
) : PhotoRepository {

    override suspend fun bookmarkToggle(bookmarkedPhoto: BookmarkedPhoto) {
        bookmarkedPhotoDao.insert(bookmarkedPhoto.toDto())
    }

    override suspend fun getPhoto(id: String): Photo {
        return photoApi.getPhoto(id).toModel()
    }

    override suspend fun searchPhotos(query: String, prevQuery: String?): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = PhotoPagingSource.DEFAULT_PER_PAGE,
            ),
            pagingSourceFactory = {
                PhotoPagingSource(
                    photoApi = photoApi,
                    query = query,
                    prevQuery = prevQuery
                )
            }
        ).flow.map { paging ->
            paging.map(PhotoResponse::toModel)
        }
    }

    override fun getBookmarkPhotos(): Flow<List<BookmarkedPhoto>> {
        return bookmarkedPhotoDao.getBookmarkedPhotos().map { list ->
            list.map(BookmarkedPhotoEntity::toModel)
        }
    }

    override suspend fun deleteBookmarkPhoto(id: String) {
        bookmarkedPhotoDao.deleteById(id)
    }
}