package com.example.destest.feature.content.data.repository

import com.example.destest.contentDto
import com.example.destest.contentDtoLessStories
import com.example.destest.contentDtoLessVideos
import com.example.destest.core.ErrorMessage
import com.example.destest.core.util.Resource
import com.example.destest.dateMax
import com.example.destest.dateMin
import com.example.destest.feature.content.data.local.StoryDao
import com.example.destest.feature.content.data.local.VideoDao
import com.example.destest.feature.content.data.remote.ContentApi
import com.example.destest.feature.content.domain.model.Story
import com.example.destest.feature.content.domain.model.Video
import com.example.destest.feature.content.domain.repository.ContentRepository
import com.example.destest.flowErrorCount
import com.example.destest.flowSuccessCount
import com.example.destest.resourceErrorIndex
import com.example.destest.resourceSuccessIndex
import com.example.destest.storiesDao
import com.example.destest.storiesDaoLess
import com.example.destest.videosDao
import com.example.destest.videosDaoLess
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ContentRepositoryImplTest {

    private lateinit var contentRepositoryImpl: ContentRepository

    private val contentApi = mockk<ContentApi>(relaxed = true)
    private val storyDao = mockk<StoryDao>(relaxed = true)
    private val videoDao = mockk<VideoDao>(relaxed = true)

    @Before
    fun setUp() {
        contentRepositoryImpl = ContentRepositoryImpl(
            contentApi,
            storyDao,
            videoDao,
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getContent flow should start from ResourceLoading`() = runTest {
        // given

        // when
        val flow = contentRepositoryImpl.getContent()
        val data = flow.first()

        // then
        assert(data is Resource.Loading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getContent flow should emit Resource 3 times`() = runTest {
        // given

        // when
        val flow = contentRepositoryImpl.getContent()
        val emits = flow.count()

        // then
        assert(emits == flowSuccessCount)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getContent Success should have content items sorted by date`() = runTest {
        // given
        contentApi.apply { coEvery { getContent() } returns contentDto }

        storyDao.apply { coEvery { getStories() } returns storiesDao }

        videoDao.apply { coEvery { getVideos() } returns videosDao }

        // when
        val flow = contentRepositoryImpl.getContent()
        val resources = flow.toList()
        val dataSuccess = resources[resourceSuccessIndex]
        val firstContentItem = dataSuccess.data?.first()
        val lastContentItem = dataSuccess.data?.last()

        // then
        firstContentItem?.let {
            assert(it.date == dateMax)
        }

        lastContentItem?.let {
            assert(it.date == dateMin)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getContent Success should have content items shuffled`() = runTest {
        // given
        contentApi.apply { coEvery { getContent() } returns contentDto }

        storyDao.apply { coEvery { getStories() } returns storiesDao }

        videoDao.apply { coEvery { getVideos() } returns videosDao }

        // when
        val flow = contentRepositoryImpl.getContent()
        val resources = flow.toList()
        val dataSuccess = resources[resourceSuccessIndex]
        val firstContentItem = dataSuccess.data?.first()
        val secondContentItem = dataSuccess.data?.get(1)

        // then
        firstContentItem?.let {
            assert(it is Video)
        }

        secondContentItem?.let {
            assert(it is Story)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getContent Success should have content items shuffled starting from story`() = runTest {
        // given
        contentApi.apply { coEvery { getContent() } returns contentDtoLessVideos }

        storyDao.apply { coEvery { getStories() } returns storiesDao }

        videoDao.apply { coEvery { getVideos() } returns videosDaoLess }

        // when
        val flow = contentRepositoryImpl.getContent()
        val resources = flow.toList()
        val dataSuccess = resources[resourceSuccessIndex]
        val firstContentItem = dataSuccess.data?.first()
        val secondContentItem = dataSuccess.data?.get(1)

        // then
        firstContentItem?.let {
            assert(it is Story)
        }

        secondContentItem?.let {
            assert(it is Video)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getContent Success should have content items shuffled starting from video`() = runTest {
        // given
        contentApi.apply { coEvery { getContent() } returns contentDtoLessStories }

        storyDao.apply { coEvery { getStories() } returns storiesDaoLess }

        videoDao.apply { coEvery { getVideos() } returns videosDao }

        // when
        val flow = contentRepositoryImpl.getContent()
        val resources = flow.toList()
        val dataSuccess = resources[resourceSuccessIndex]
        val firstContentItem = dataSuccess.data?.first()
        val secondContentItem = dataSuccess.data?.get(1)

        // then
        firstContentItem?.let {
            assert(it is Video)
        }

        secondContentItem?.let {
            assert(it is Story)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getContent Success should have content all story and video items`() = runTest {
        // given
        contentApi.apply { coEvery { getContent() } returns contentDtoLessStories }

        storyDao.apply { coEvery { getStories() } returns storiesDaoLess }

        videoDao.apply { coEvery { getVideos() } returns videosDao }

        // when
        val flow = contentRepositoryImpl.getContent()
        val resources = flow.toList()
        val dataSuccess = resources[resourceSuccessIndex]

        // then
        dataSuccess.data?.size.let {
            assert(it == contentDtoLessStories.stories.size + contentDtoLessStories.videos.size)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getContent should emit Resource Error on contentApi error`() = runTest {
        // given
        contentApi.apply { coEvery { getContent() } throws IOException() }

        // when
        val flow = contentRepositoryImpl.getContent()
        val resources = flow.toList()
        val dataError = resources[resourceErrorIndex]

        // then
        assert(resources.size == flowErrorCount)
        assert(dataError is Resource.Error)
        dataError.message?.let {
            assert(it == ErrorMessage.IO_EXCEPTION.message)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getContent should call once contentApi getContent`() = runTest {
        // given

        // when
        contentRepositoryImpl.getContent().toList()

        // then
        coVerify(exactly = 1) { contentApi.getContent() }
    }
}
