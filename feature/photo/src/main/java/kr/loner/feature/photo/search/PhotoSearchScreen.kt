package kr.loner.feature.photo.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kr.loner.feature.photo.R
import kr.loner.feature.photo.ui.PhotoList
import kr.loner.willog.designsystem.component.SearchBar
import kr.loner.willog.model.Photo

@Composable
internal fun PhotoSearchRoute(
    padding: PaddingValues,
    onChangeAppTitle: (String) -> Unit,
    onPhotoClick: (Photo) -> Unit,
    onBookmarkClick: () -> Unit,
    viewModel: PhotoSearchViewModel = hiltViewModel()
) {
    val photos = viewModel.searchPhotos.collectAsLazyPagingItems()

    onChangeAppTitle(stringResource(id = R.string.photo_search_title))
    PhotoSearchScreen(
        padding = padding,
        photos = photos,
        onPhotoClick = onPhotoClick,
        onBookmarkClick = onBookmarkClick,
        onSearchTextChange = { text -> viewModel.setQuery(text) },
    )
}

@Composable
private fun PhotoSearchScreen(
    padding: PaddingValues,
    photos: LazyPagingItems<Photo>,
    onSearchTextChange: (String) -> Unit,
    onPhotoClick: (Photo) -> Unit,
    onBookmarkClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Column {
            SearchBar {
                onSearchTextChange(it)
            }

            PhotoList(
                photos = photos,
                onPhotoClick = onPhotoClick
            )
        }
        BookmarkButton(onBookmarkClick)
    }
}

@Composable
private fun BookmarkButton(onBookmarkClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        SmallFloatingActionButton(
            onClick = { onBookmarkClick() },
            modifier = Modifier
                .background(color = Color.White)
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp, bottom = 12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_heart),
                modifier = Modifier.size(24.dp),
                contentDescription = "북마크 아이콘"
            )
        }
    }

}