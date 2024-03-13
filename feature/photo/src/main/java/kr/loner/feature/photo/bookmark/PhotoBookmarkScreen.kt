package kr.loner.feature.photo.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.loner.feature.photo.R
import kr.loner.feature.photo.ui.PhotoList
import kr.loner.willog.designsystem.component.Loading
import kr.loner.willog.designsystem.theme.WillogTheme
import kr.loner.willog.model.Photo

@Composable
internal fun PhotoBookmarkRoute(
    padding: PaddingValues,
    onChangeScreen: (String) -> Unit,
    onPhotoClick: (Photo) -> Unit,
    viewModel: PhotoBookmarkViewModel = hiltViewModel()
) {
    onChangeScreen(stringResource(id = R.string.photo_bookmark_title))

    val photoBookmarkUiState by viewModel.photoBookmarkUiState.collectAsStateWithLifecycle()
    val state = photoBookmarkUiState as? PhotoBookmarkUiState.Success
    if (state != null) {
        PhotoBookmarkScreen(
            padding = padding,
            state = state,
            onPhotoClick = onPhotoClick
        )
    } else {
        Loading()
    }

}

@Composable
private fun PhotoBookmarkScreen(
    padding: PaddingValues,
    state: PhotoBookmarkUiState.Success,
    onPhotoClick: (Photo) -> Unit
) {
    PhotoList(
        padding = padding,
        photos = state.photos
    ) { photo ->
        onPhotoClick(photo)
    }
    if (state.photos.isEmpty()) PhotoBookmarkListEmpty()

}

@Composable
private fun PhotoBookmarkListEmpty() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.photo_bookmark_empty_text),
            style = WillogTheme.typography.title
        )
    }
}

