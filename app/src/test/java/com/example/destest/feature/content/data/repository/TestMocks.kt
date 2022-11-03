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
const val flowErrorCount = 3

val storyDto1 = StoryDto("Author1", 10000.0, 1, "image1", SportDto(1, "sport1"), "teaser1", "title1")
val storyDto2MinDate = StoryDto("Author2", dateMin, 2, "image2", SportDto(2, "sport2"), "teaser2", "title2")
val storyDto3 = StoryDto("Author3", 10100.0, 3, "image3", SportDto(3, "sport3"), "teaser3", "title3")

val storiesDto = listOf(storyDto1, storyDto2MinDate, storyDto3)
val storiesDtoLess = listOf(storyDto1, storyDto2MinDate)

val videoDto1 = VideoDto(10090.0, 4, SportDto(4, "sport4"), "thumb4", "title4", "url4", 100)
val videoDto2MaxDate = VideoDto(dateMax, 5, SportDto(5, "sport5"), "thumb5", "title5", "url5", 200)
val videoDto3 = VideoDto(10040.0, 6, SportDto(6, "sport6"), "thumb6", "title6", "url6", 300)

val videosDto = listOf(videoDto1, videoDto2MaxDate, videoDto3)
val videosDtoLess = listOf(videoDto1, videoDto2MaxDate)

val storyEntity1 = StoryEntity(1, "Author1", 10000.0, "image1", "sport1", "teaser1", "title1")
val storyEntity2MinDate = StoryEntity(2, "Author2", dateMin, "image2", "sport2", "teaser2", "title2")
val storyEntity3 = StoryEntity(3, "Author3", 10100.0, "image3", "sport3", "teaser3", "title3")

val storiesDao = listOf(storyEntity1, storyEntity2MinDate, storyEntity3)
val storiesDaoLess = listOf(storyEntity1, storyEntity2MinDate)

val videoEntity1 = VideoEntity(4, 10090.0, "sport4", "thumb4", "title4", "url4", 100)
val videoEntity2MaxDate = VideoEntity(5, dateMax, "sport5", "thumb5", "title5", "url5", 200)
val videoEntity3 = VideoEntity(6, 10040.0, "sport6", "thumb6", "title6", "url6", 300)

val videosDao = listOf(videoEntity1, videoEntity2MaxDate, videoEntity3)
val videosDaoLess = listOf(videoEntity1, videoEntity2MaxDate)

val contentDto = ContentDto(
    stories = storiesDto,
    videos = videosDto,
)

val contentDtoLessStories = ContentDto(
    stories = storiesDtoLess,
    videos = videosDto,
)

val contentDtoLessVideos = ContentDto(
    stories = storiesDto,
    videos = videosDtoLess,
)
