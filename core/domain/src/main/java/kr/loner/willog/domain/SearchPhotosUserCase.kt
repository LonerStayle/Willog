package kr.loner.willog.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kr.loner.willog.data.repository.PhotoRepository
import kr.loner.willog.model.Photo
import javax.inject.Inject

class SearchPhotosUserCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(query: String, prevQuery: String?): Flow<PagingData<Photo>> =
        photoRepository.searchPhotos(
            query = query,
            prevQuery = prevQuery
        )

}