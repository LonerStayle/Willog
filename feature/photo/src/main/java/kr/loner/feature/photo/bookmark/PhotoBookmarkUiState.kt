package kr.loner.feature.photo.bookmark

import kr.loner.willog.model.Photo

interface PhotoBookmarkUiState {
    object Loading : PhotoBookmarkUiState
    data class Success(val photos: List<Photo>) : PhotoBookmarkUiState
}