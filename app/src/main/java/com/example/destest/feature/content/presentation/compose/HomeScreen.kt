package com.example.destest.feature.content.presentation.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.content.domain.model.Video
import com.example.destest.feature.content.presentation.ContentViewModel
import com.example.destest.ui.theme.DarkBlue
import com.example.destest.ui.theme.White
import kotlinx.coroutines.flow.collectLatest

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(DarkBlue),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.getHeader(),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = White,
            )
        }
        val lazyListState = rememberLazyListState()
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .semantics {
                    contentDescription = "scrollContainer"
                }
        ) {
            itemsIndexed(
                items = state.content,
                itemContent = { _, contentItem ->
                    when (contentItem) {
                        is Story -> StoryContentItem(navController, contentItem)
                        is Video -> VideoContentItem(navController, contentItem)
                    }
                }
            )
        }
    }
}
