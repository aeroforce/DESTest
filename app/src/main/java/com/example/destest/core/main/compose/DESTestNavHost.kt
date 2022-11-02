package com.example.destest.core.main.compose

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.destest.core.extension.hideSystemUI
import com.example.destest.core.extension.showSystemUI
import com.example.destest.core.main.AppRoute
import com.example.destest.core.main.AppRouteParameter
import com.example.destest.feature.content.presentation.compose.HomeScreen
import com.example.destest.feature.player.presentation.compose.PlayerScreen
import com.example.destest.feature.story.presentation.compose.StoryScreen

@Composable
fun DESTestNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppRoute.HOME.route,
) {
    val context = LocalContext.current
    val activity = context as Activity
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(AppRoute.HOME.route) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            activity.showSystemUI()
            HomeScreen(navController)
        }
        composable(
            route = "${AppRoute.STORY.route}/{${AppRouteParameter.STORY.param}}",
            arguments = listOf(navArgument(AppRouteParameter.STORY.param) { type = NavType.IntType })
        ) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            activity.showSystemUI()
            StoryScreen(navController)
        }
        composable(
            route = "${AppRoute.PLAYER.route}/{${AppRouteParameter.PLAYER.param}}",
            arguments = listOf(navArgument(AppRouteParameter.PLAYER.param) { type = NavType.IntType })
        ) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            activity.hideSystemUI()
            PlayerScreen(navController)
        }
    }
}
