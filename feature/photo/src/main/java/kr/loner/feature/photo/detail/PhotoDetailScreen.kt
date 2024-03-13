package kr.loner.feature.photo.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kr.loner.feature.photo.R
import kr.loner.willog.designsystem.component.Loading
import kr.loner.willog.designsystem.component.NetworkImage
import kr.loner.willog.designsystem.theme.WillogTheme

@Composable
internal fun PhotoDetailRoute(
    onChangeScreen: (String) -> Unit,
    padding: PaddingValues,
    photoId: String,
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    val photoDetailUiState by viewModel.photoDetailUiState.collectAsStateWithLifecycle()
    onChangeScreen(stringResource(id = R.string.photo_detail_title))

    PhotoDetailScreen(padding, photoDetailUiState) {
        viewModel.toggleBookmark()
    }

    LaunchedEffect(photoId) {
        viewModel.fetchPhoto(photoId)
    }
}

@Composable
private fun PhotoDetailScreen(
    padding: PaddingValues,
    photoDetailUiState: PhotoDetailUiState,
    onBookmarkClick: () -> Unit
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        val state = (photoDetailUiState as? PhotoDetailUiState.Success)
        if (state != null) {
            PhotoDetailContent(state, onBookmarkClick)
        } else {
            Loading()
        }
    }

}

@Composable()
private fun PhotoDetailContent(
    state: PhotoDetailUiState.Success,
    onBookmarkClick: () -> Unit,
) {
    Column(
        Modifier.padding(20.dp)
    ) {
        NetworkImage(
            imageUrl = state.photo.urls.full,
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .fillMaxWidth()
                .height(300.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        PhotoDetailContentDescriptions(state)
        PhotoDetailBookmarkButton(onBookmarkClick, state)
    }
}

@Composable
private fun PhotoDetailBookmarkButton(
    onBookmarkClick: () -> Unit,
    state: PhotoDetailUiState.Success
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        FloatingActionButton(
            onClick = {
                onBookmarkClick()
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            val iconRes = if (state.photo.isBookmark) {
                R.drawable.ic_heart
            } else {
                R.drawable.ic_heart_outline
            }
            Icon(
                painter = painterResource(id = iconRes),
                tint = Color.Red,
                contentDescription = "북마크 버튼",
                modifier = Modifier.size(18.dp)
            )
        }
    }

}

@Composable
private fun PhotoDetailContentDescriptions(state: PhotoDetailUiState.Success) {
    val photo = state.photo
    Column(
        modifier = Modifier.background(
            color = Color.White, shape = RoundedCornerShape(12.dp)
        )
    ) {
        val labelAndContents = mapOf(
            R.string.photo_detail_id to photo.id,
            R.string.photo_detail_author to photo.user.username,
            R.string.photo_detail_size to "${photo.width} x ${photo.height}",
            R.string.photo_detail_create_at to photo.createdAt
        )
        labelAndContents.entries.forEachIndexed { i, entry ->
            if (i != 0) Spacer(modifier = Modifier.height(0.5.dp))

            PhotoDetailDescription(
                label = stringResource(id = entry.key), content = entry.value
            )
        }
    }
}

@Composable
private fun PhotoDetailDescription(label: String, content: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label, style = WillogTheme.typography.title
        )
        Text(
            text = content,
            style = WillogTheme.typography.body,
        )
    }
}

