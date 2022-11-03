package com.example.destest

import com.example.destest.feature.content.data.local.entity.StoryEntity
import com.example.destest.feature.content.data.local.entity.VideoEntity
import com.example.destest.feature.content.data.remote.dto.ContentDto
import com.example.destest.feature.content.data.remote.dto.SportDto
import com.example.destest.feature.content.data.remote.dto.StoryDto
import com.example.destest.feature.content.data.remote.dto.VideoDto

const val dateMin = 9000.0
const val dateMax = 11000.0

const val resourceErrorIndex = 2
const val resourceSuccessIndex = 2
const val flowSuccessCount = 3
const val flowErrorCount = 4

val storiesDto = listOf(
    StoryDto("Author1", 10000.0, 1, "image1", SportDto(1, "sport1"), "teaser1", "title1"),
    StoryDto("Author2", dateMin, 2, "image2", SportDto(2, "sport2"), "teaser2", "title2"),
    StoryDto("Author3", 10100.0, 3, "image3", SportDto(3, "sport3"), "teaser3", "title3"),
)

val videosDto = listOf(
    VideoDto(10090.0, 4, SportDto(4, "sport4"), "thumb4", "title4", "url4", 100),
    VideoDto(dateMax, 5, SportDto(5, "sport5"), "thumb5", "title5", "url5", 200),
    VideoDto(10040.0, 6, SportDto(6, "sport6"), "thumb6", "title6", "url6", 300),
)

val storiesDao = listOf(
    StoryEntity(1, "Author1", 10000.0, "image1", "sport1", "teaser1", "title1"),
    StoryEntity(2, "Author2", dateMin, "image2", "sport2", "teaser2", "title2"),
    StoryEntity(3, "Author3", 10100.0, "image3", "sport3", "teaser3", "title3"),
)

val videosDao = listOf(
    VideoEntity(4, 10090.0, "sport4", "thumb4", "title4", "url4", 100),
    VideoEntity(5, dateMax, "sport5", "thumb5", "title5", "url5", 200),
    VideoEntity(6, 10040.0, "sport6", "thumb6", "title6", "url6", 300),
)

val contentDto = ContentDto(
    stories = storiesDto,
    videos = videosDto,
)
