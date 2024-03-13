package kr.loner.feature.photo.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.loner.feature.photo.R
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

    /*아래 변수는 미사용,
     * Paging3 를 Compose 로 구현 중에
     * 페이지 네이션이 되지 않는 버그 원인을 찾지 못함 시간이 없어서 xml로 선구현..
     *
     val photos = viewModel.searchPhotos.collectAsLazyPagingItems()
     */

    val effect by viewModel.photoSearchUiEffect.collectAsStateWithLifecycle()
    val scrollState = rememberLazyGridState()

    /* Xml 페이징3 구현용 코드 (1)*/
    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current
    val photoItemHeight = (configuration.screenWidthDp.dp.value.toInt() / 1.5).toInt()
    val searchAdapter = remember {
        PhotoSearchAdapter(photoItemHeight, onPhotoClick)
    }


    onChangeAppTitle(stringResource(id = R.string.photo_search_title))
    PhotoSearchScreen(
        searchAdapter = searchAdapter,
        padding = padding,
        onBookmarkClick = onBookmarkClick,
        onSearchTextChange = { text -> viewModel.setQuery(text) },
    )

    LaunchedEffect(effect) {
        if (effect is PhotoSearchEffect.RefreshPaging) {
            launch(Dispatchers.Main) { searchAdapter.refresh() }
        }
    }

    /* Xml 페이징3 구현용 코드 (2) */
    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchPhotos.collect { photos ->
                    Log.d("checkk오니??",photos.toString())
                    searchAdapter.submitData(photos)
                }
            }
        }
    }
}

@Composable
private fun PhotoSearchScreen(
    searchAdapter: PhotoSearchAdapter,
    padding: PaddingValues,
    onSearchTextChange: (String) -> Unit,
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

            AndroidView(
                modifier = Modifier.fillMaxWidth(),
                factory = { context ->
                    val recyclerView = RecyclerView(context).apply {
                        layoutManager = GridLayoutManager(context, 4)
                        adapter = searchAdapter
                        addItemDecoration(
                            PhotoSearchAdapter.GridSpacingItemDecoration(
                                spanCount = 4,
                                spacing = 4.dp.value.toInt()
                            )
                        )
                    }
                    recyclerView
                })
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