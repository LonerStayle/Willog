package kr.loner.willog.domain

import kr.loner.willog.data.repository.PhotoRepository
import kr.loner.willog.model.Photo
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {
    suspend operator fun invoke(id: String):Photo = photoRepository.getPhoto(id)

}