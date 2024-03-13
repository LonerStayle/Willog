package kr.loner.willog.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import kr.loner.feature.photo.photoGraph
import kr.loner.willog.designsystem.component.TopBar


@Composable
internal fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
    viewModel: MainViewModel = hiltViewModel()
) {
    val appBarText by viewModel.appBarText.collectAsState()

    Scaffold(
        topBar = {
            TopBar(appBarText)
        },
        content = { padding ->
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                NavHost(
                    navController = navigator.navController,
                    startDestination = navigator.startRoute
                ) {
                    photoGraph(
                        onChangeScreen = { text ->
                            viewModel.updateAppBarText(text)
                        },
                        padding = padding,
                        onPhotoClick = { navigator.navigatePhotoDetail(it.id) },
                        onBookmarkClick = { navigator.navigatePhotoBookmark() }
                    )
                }
            }
        })
}