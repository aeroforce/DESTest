package com.example.destest.feature.content.presentation.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.content.domain.model.Video
import com.example.destest.feature.content.presentation.ContentViewModel
import kotlinx.coroutines.flow.collectLatest

private val dimens = object {
    val listPaddingTop = 10.dp
}

private val ids = object {
    val listScrollContainer = "scrollContainer"
}

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val viewModel: ContentViewModel = hiltViewModel()
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ContentViewModel.UIEvent.ShowSnackBar -> {
                    Log.d("AERODEBUG", event.message)
                }
            }
        }
    }

    Column(
        modifier = Modifier.background(Color.LightGray).fillMaxSize()
    ) {
        Header(title = viewModel.getHeader())

        val lazyListState = rememberLazyListState()
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimens.listPaddingTop)
                .semantics { contentDescription = ids.listScrollContainer },
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.content) {
                when (it) {
                    is Story -> StoryItem(navController, it)
                    is Video -> VideoItem(navController, it)
                }
            }
        }
    }
    if (state.isLoading) {
        LoadingOverlay()
    }
}
