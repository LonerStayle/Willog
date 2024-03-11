package kr.loner.willog.domain

import kr.loner.willog.data.repository.PhotoRepository
import kr.loner.willog.model.BookmarkedPhoto
import javax.inject.Inject

class BookmarkToggleUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(bookmarkedPhoto: BookmarkedPhoto)  {
        photoRepository.bookmarkToggle(bookmarkedPhoto)
    }
}