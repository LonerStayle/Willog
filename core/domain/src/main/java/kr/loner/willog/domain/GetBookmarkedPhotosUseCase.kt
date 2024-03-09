package kr.loner.willog.domain

import kotlinx.coroutines.flow.Flow
import kr.loner.willog.data.repository.PhotoRepository
import kr.loner.willog.model.BookmarkedPhoto
import javax.inject.Inject

class GetBookmarkedPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    operator fun invoke(): Flow<List<BookmarkedPhoto>> = photoRepository.getBookmarkPhotos()
}