package kr.loner.willog.feature.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import kr.loner.willog.designsystem.component.TopBar


@Composable
internal fun MainScreen(){

    Scaffold(
        topBar = {
            TopBar("목록")
        },
        content ={ padding ->
                 padding

        },

    )
}