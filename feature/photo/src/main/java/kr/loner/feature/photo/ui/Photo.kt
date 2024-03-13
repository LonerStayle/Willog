package kr.loner.feature.photo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import kr.loner.feature.photo.R
import kr.loner.willog.designsystem.component.NetworkImage
import kr.loner.willog.model.Photo
import kr.loner.willog.model.User


@Composable
fun PhotoList(
    lazyGridState: LazyGridState = rememberLazyGridState(),
    padding: PaddingValues = PaddingValues(),
    photos: List<Photo>,
    onPhotoClick: (Photo) -> Unit
) {
    LazyVerticalGrid(
        state = lazyGridState,
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = padding
    ) {
        items(
            items = photos,
            key = (Photo::id)
        ) { photo ->
            PhotoItem(
                photo,
                onPhotoClick
            )
        }
    }
}


@Composable
internal fun PhotoItem(photo: Photo, onPhotoClick: (Photo) -> Unit) {
    val configuration = LocalConfiguration.current
    val imageHeightSize = configuration.screenWidthDp.dp / 4
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(imageHeightSize)
            .clickable {
                onPhotoClick(photo)
            }
    ) {
        NetworkImage(
            imageUrl = photo.urls.thumb,
            modifier = Modifier.fillMaxSize(),
            contentDescription = "ThumbNail"
        )
        if (photo.isBookmark) {
            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 6.dp, end = 6.dp)
                    .size(18.dp),
                contentDescription = "Bookmark",
            )
        }
    }
}


private val mockPhoto = Photo(
    id = "asd",
    urls = Photo.Urls(
        "",
        "https://i.namu.wiki/i/4Mlxj6PmC-VGpH89-MVlhAEezBnrd5vMiYjF6HOEWEyIPeui5oSLYgRyqaOlMKy4Ss0jSz1LZBxkP549NvOsWA.webp"
    ),
    user = User("", ""),
    width = 0,
    height = 0,
    createdAt = "",
    isBookmark = true
)

@Preview
@Composable
private fun PhotoItemPreview() {
    PhotoItem(mockPhoto) {

    }

}

@Preview
@Composable
fun PhotoListPreview() {
    val testList = mutableListOf<Photo>()
    repeat(100) {
        testList.add(mockPhoto)
    }
    PhotoList(
        photos = testList
    ) {

    }
}