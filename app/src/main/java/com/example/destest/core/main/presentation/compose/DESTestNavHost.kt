package com.example.destest.core.main.presentation.compose

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.destest.core.main.presentation.MainActivityViewModel
import com.example.destest.feature.content.presentation.compose.HomeScreen
import com.example.destest.feature.content.presentation.compose.LoadingOverlay
import com.example.destest.feature.player.presentation.compose.PlayerScreen
import com.example.destest.feature.story.presentation.compose.StoryScreen

@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun DESTestNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppRoute.HOME.route,
) {
    val context = LocalContext.current
    val viewModel: MainActivityViewModel = hiltViewModel()
    val activity = context as Activity

    Box {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination,
        ) {
            composable(AppRoute.HOME.route) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                activity.showSystemUI()
                HomeScreen(navController)
            }
            composable(
                route = "${AppRoute.STORY.route}/{${AppRouteParameter.STORY.param}}",
                arguments = listOf(
                    navArgument(AppRouteParameter.STORY.param) {
                        type = NavType.IntType
                    }
                )
            ) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                activity.showSystemUI()
                StoryScreen(onBackClick = { navController.popBackStack() })
            }
            composable(
                route = "${AppRoute.PLAYER.route}/{${AppRouteParameter.PLAYER.param}}",
                arguments = listOf(
                    navArgument(AppRouteParameter.PLAYER.param) {
                        type = NavType.IntType
                    }
                )
            ) {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                activity.hideSystemUI()
                PlayerScreen(onBackClick = { navController.popBackStack() })
            }
        }
        if (viewModel.busyState) {
            LoadingOverlay()
        }
        if (viewModel.connectionProblemState) {
            ConnectionProblemInfo(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}
