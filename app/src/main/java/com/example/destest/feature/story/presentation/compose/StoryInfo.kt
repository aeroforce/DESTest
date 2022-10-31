package com.example.destest.feature.story.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.content.domain.model.getDate
import com.example.destest.ui.theme.Black

private val dimens = object {
    val paddingStart = 20.dp
    val teaserPadding = 20.dp
}

private val styles = object {
    val title = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Black,
    )

    val author = TextStyle(
        fontSize = 12.sp,
        color = Black,
    )

    val date = TextStyle(
        fontSize = 10.sp,
        color = Black,
    )

    val teaser = TextStyle(
        color = Black,
    )
}

private val strings = object {
    val by = "By"
}

@Composable
fun StoryInfo(modifier: Modifier = Modifier, story: Story) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimens.paddingStart),
            text = story.title,
            style = styles.title,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimens.paddingStart),
            text = "${strings.by} ${story.author}",
            style = styles.author,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimens.paddingStart),
            text = story.getDate(),
            style = styles.date,
        )
        Text(
            modifier = Modifier
                .padding(dimens.teaserPadding),
            text = story.teaser,
            style = styles.teaser,
        )
    }
}
