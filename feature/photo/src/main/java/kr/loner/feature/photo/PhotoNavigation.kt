package kr.loner.feature.photo

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kr.loner.feature.photo.bookmark.PhotoBookmarkRoute
import kr.loner.feature.photo.detail.PhotoDetailRoute
import kr.loner.feature.photo.search.PhotoSearchRoute
import kr.loner.willog.model.Photo

fun NavGraphBuilder.photoGraph(
    padding: PaddingValues,
    onChangeScreen: (String) -> Unit,
    onPhotoClick: (Photo) -> Unit,
    onBookmarkClick: () -> Unit
) {

    composable(PhotoRoute.Search.route) {
        PhotoSearchRoute(padding, onChangeScreen, onPhotoClick, onBookmarkClick)
    }

    composable(PhotoRoute.Bookmark.route) {
        PhotoBookmarkRoute(padding, onChangeScreen, onPhotoClick)
    }

    composable(
        route = PhotoRoute.Detail("{id}").route,
        arguments = listOf(
            navArgument("id") { type = NavType.StringType }
        )
    ) { stackEntry ->
        val id = stackEntry.arguments?.getString("id") ?: ""
        PhotoDetailRoute(onChangeScreen, padding, id)
    }
}


fun NavController.navigatePhotoBookmark() {
    navigate(PhotoRoute.Bookmark.route)
}

fun NavController.navigatePhotoDetail(photoId: String) {
    navigate(PhotoRoute.Detail(photoId).route)
}

sealed class PhotoRoute(route: String) {
    private val host = "photo"
    val route: String = "$host/$route"

    object Search : PhotoRoute("search")
    object Bookmark : PhotoRoute("bookmark")
    data class Detail(val id: String) : PhotoRoute(id)
}