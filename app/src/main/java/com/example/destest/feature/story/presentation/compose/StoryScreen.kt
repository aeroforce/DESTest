package com.example.destest.feature.story.presentation.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.destest.feature.content.domain.model.getDate
import com.example.destest.feature.content.presentation.compose.SportTag
import com.example.destest.feature.story.presentation.StoryViewModel
import com.example.destest.ui.theme.Black
import com.example.destest.ui.theme.White
import kotlinx.coroutines.flow.collectLatest

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
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 250.dp),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                text = state.story.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Black,
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                text = "By ${state.story.author}",
                fontSize = 12.sp,
                color = Black,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                text = state.story.getDate(),
                fontSize = 10.sp,
                color = Black,
            )
            Text(
                modifier = Modifier
                    // .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                text = state.story.teaser,
                color = Black,
            )
        }
        SportTag(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 220.dp, start = 20.dp),
            sport = state.story.sport,
        )
    }
    if (state.isLoading) {
        Text("Loading....")
    }
}
