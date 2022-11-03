package com.example.destest.feature.story.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.content.presentation.compose.SportTag
import com.example.destest.feature.story.presentation.StoryViewModel
import com.example.destest.ui.theme.White

private val dimens = object {
    val storyPaddingTop = 250.dp
    val sportTagPaddingStart = 20.dp
    val sportTagPaddingTop = 220.dp
}

@Composable
fun StoryScreen(onBackClick :() -> Unit = {}) {
    val viewModel = hiltViewModel<StoryViewModel>()
    StoryComponent(story = viewModel.story, onBackClick = onBackClick)
}

@Composable
private fun StoryComponent(story: Story, onBackClick :() -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        StoryPoster(
            onBackClick = onBackClick,
            modifier = Modifier.align(Alignment.TopCenter),
            image = story.image,
            description = story.title,
        )
        StoryInfo(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = dimens.storyPaddingTop),
            story = story
        )
        SportTag(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = dimens.sportTagPaddingTop, start = dimens.sportTagPaddingStart),
            sport = story.sport,
        )
    }
}
