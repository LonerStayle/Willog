package kr.loner.feature.photo.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.loner.willog.domain.BookmarkToggleUseCase
import kr.loner.willog.domain.GetPhotoUseCase
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase,
    private val bookmarkToggleUseCase: BookmarkToggleUseCase
) : ViewModel() {

    private val _photoDetailUiState =
        MutableStateFlow<PhotoDetailUiState>(PhotoDetailUiState.Loading)
    val photoDetailUiState: StateFlow<PhotoDetailUiState> get() = _photoDetailUiState

    fun fetchPhoto(photoId: String) {
        viewModelScope.launch {
            val photo = getPhotoUseCase(photoId)
            _photoDetailUiState.value = PhotoDetailUiState.Success(photo)
        }
    }

    fun bookmarkToggle() {
        viewModelScope.launch {
            val photo = (photoDetailUiState.value as PhotoDetailUiState.Success).photo
            bookmarkToggleUseCase(photo)
            fetchPhoto(photo.id)
        }
    }


}