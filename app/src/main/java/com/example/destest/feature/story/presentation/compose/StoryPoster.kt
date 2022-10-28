package com.example.destest.feature.story.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.destest.R

@Composable
fun StoryPoster(
    navController: NavController,
    modifier: Modifier = Modifier,
    image: String,
    description: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(240.dp),
            model = image,
            contentDescription = description,
            contentScale = ContentScale.Crop,
        )
        Image(
            modifier = Modifier
                .height(50.dp)
                .align(Alignment.TopStart)
                .padding(top = 12.dp, start = 8.dp)
                .clickable { navController.popBackStack() },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back",
        )
        Image(
            modifier = Modifier
                .height(50.dp)
                .align(Alignment.TopEnd)
                .padding(top = 12.dp, end = 8.dp),
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "Back",
        )
    }
}
