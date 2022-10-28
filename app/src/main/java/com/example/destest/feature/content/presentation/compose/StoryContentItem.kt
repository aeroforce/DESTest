package com.example.destest.feature.content.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.destest.core.main.AppRoute
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.content.domain.model.getDate
import com.example.destest.ui.theme.Black
import com.example.destest.ui.theme.White

@Composable
fun StoryContentItem(navController: NavController, contentItem: Story) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .background(White)
            .clickable { navController.navigate("${AppRoute.STORY.route}/${contentItem.id}") }
    ) {
        ContentItemPoster(modifier = Modifier.align(Alignment.TopCenter), contentItem.image, contentItem.title)
        Column(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(top = 200.dp)) {

            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = contentItem.title,
                color = Black,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                text = "By ${contentItem.author} - ${contentItem.getDate()}",
                color = Color.LightGray,
                fontSize = 12.sp
            )
        }
        SportTag(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 10.dp, top = 165.dp),
            contentItem.sport
        )
    }
    Box(modifier = Modifier.fillMaxWidth().height(10.dp))
}
