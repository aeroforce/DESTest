package com.example.destest.feature.content.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.destest.R
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.content.domain.model.getDate

private val dimens = object {
    val titlePaddingHorizontal = 10.dp
    val authorPadding = 10.dp
}

private val strings = object : Any(){
    @Composable
    fun by() = stringResource(id = R.string.content_by)
}

@Composable
fun StoryItemInfo(
    modifier: Modifier = Modifier,
    story: Story,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = dimens.titlePaddingHorizontal),
            text = story.title,
            style = ItemInfoStyles.titleStyle,
        )
        Text(
            modifier = Modifier.fillMaxWidth().padding(dimens.authorPadding),
            text = "${strings.by()} ${story.author} - ${story.getDate()}",
            style = ItemInfoStyles.footerStyle,
        )
    }
}
