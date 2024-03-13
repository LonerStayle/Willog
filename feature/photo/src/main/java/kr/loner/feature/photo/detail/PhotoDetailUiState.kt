package kr.loner.feature.photo.detail

import kr.loner.willog.model.Photo

interface PhotoDetailUiState {
    object Loading : PhotoDetailUiState
    data class Success(val photo: Photo) : PhotoDetailUiState
}