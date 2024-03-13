package kr.loner.feature.photo.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.loner.willog.domain.GetBookmarkedPhotosUseCase
import javax.inject.Inject

@HiltViewModel
class PhotoBookmarkViewModel @Inject constructor(
    getBookmarkedPhotosUseCase: GetBookmarkedPhotosUseCase
) : ViewModel() {
    private val _photoBookmarkUiState =
        MutableStateFlow<PhotoBookmarkUiState>(PhotoBookmarkUiState.Loading)
    val photoBookmarkUiState: StateFlow<PhotoBookmarkUiState> get() = _photoBookmarkUiState

    init {
        viewModelScope.launch {
            getBookmarkedPhotosUseCase().collect { photos ->
                _photoBookmarkUiState.value = PhotoBookmarkUiState.Success(photos)
            }
        }
    }
}