package com.example.destest.feature.story.presentation.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.destest.feature.content.presentation.compose.LoadingOverlay
import com.example.destest.feature.content.presentation.compose.SportTag
import com.example.destest.feature.story.presentation.StoryViewModel
import com.example.destest.ui.theme.White
import kotlinx.coroutines.flow.collectLatest

private val dimens = object {
    val storyPaddingTop = 250.dp
    val sportTagPaddingStart = 20.dp
    val sportTagPaddingTop = 220.dp
}

@Composable
fun StoryScreen(navController: NavController) {
    val viewModel = hiltViewModel<StoryViewModel>()
    val state = viewModel.state.value
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is StoryViewModel.UIEvent.ShowSnackBar -> {
                    Log.d("AERODEBUG", event.message)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        StoryPoster(
            navController = navController,
            modifier = Modifier.align(Alignment.TopCenter),
            image = state.story.image,
            description = state.story.title,
        )
        StoryInfo(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = dimens.storyPaddingTop),
            story = state.story
        )
        SportTag(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = dimens.sportTagPaddingTop, start = dimens.sportTagPaddingStart),
            sport = state.story.sport,
        )
        if (state.isLoading) {
            LoadingOverlay()
        }
        Log.d("AERODEBUG", "Story screen connectionProblem state: ${state.isConnectionProblem} and state : $state")
        if (state.isConnectionProblem) {
            ConnectionProblemInfo(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}
