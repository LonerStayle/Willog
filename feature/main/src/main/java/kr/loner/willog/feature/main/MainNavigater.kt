package kr.loner.willog.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
) = remember(navController) { MainNavigator(navController) }
internal class MainNavigator(
    val navController: NavHostController
) {

}


