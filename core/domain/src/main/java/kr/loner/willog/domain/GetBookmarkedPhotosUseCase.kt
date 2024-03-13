package kr.loner.willog.domain

import kotlinx.coroutines.flow.Flow
import kr.loner.willog.data.repository.PhotoRepository
import kr.loner.willog.model.Photo
import javax.inject.Inject

class GetBookmarkedPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    operator fun invoke(): Flow<List<Photo>> = photoRepository.getBookmarkPhotos()
}