package com.example.destest.feature.content.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.destest.R
import com.example.destest.core.main.AppRoute
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.content.domain.model.Video
import com.example.destest.feature.content.presentation.ContentViewModel

private val dimens = object {
    val listPaddingTop = 10.dp
}

private val ids = object {
    val listScrollContainer = "scrollContainer"
}

private val strings = object : Any() {
    @Composable
    fun header() = stringResource(id = R.string.content_header)
}

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: ContentViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
    ) {
        Header(title = strings.header())
        val lazyListState = rememberLazyListState()
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimens.listPaddingTop)
                .semantics { contentDescription = ids.listScrollContainer },
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(viewModel.content) {
                when (it) {
                    is Story -> StoryItem(story = it, onCLick = { navController.navigate("${AppRoute.STORY.route}/${it.id}") })
                    is Video -> VideoItem(video = it, onClick = { navController.navigate("${AppRoute.PLAYER.route}/${it.id}") })
                }
            }
        }
    }
}
