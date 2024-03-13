package kr.loner.willog.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kr.loner.feature.photo.PhotoRoute
import kr.loner.feature.photo.navigatePhotoBookmark
import kr.loner.feature.photo.navigatePhotoDetail
import kr.loner.feature.photo.navigatePhotoSearch

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
) = remember(navController) { MainNavigator(navController) }

internal class MainNavigator(
    val navController: NavHostController
) {
    val startRoute = PhotoRoute.Search.route

    fun navigatePhotoSearch() {
        navController.navigatePhotoSearch()
    }

    fun navigatePhotoDetail(photoId: String) {
        navController.navigatePhotoDetail(photoId)
    }

    fun navigatePhotoBookmark() {
        navController.navigatePhotoBookmark()
    }
}


