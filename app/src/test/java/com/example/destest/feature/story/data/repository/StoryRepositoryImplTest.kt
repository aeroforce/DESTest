package com.example.destest.feature.story.data.repository

import com.example.destest.core.ErrorMessage
import com.example.destest.core.util.Resource
import com.example.destest.feature.content.data.local.StoryDao
import com.example.destest.feature.content.data.remote.ContentApi
import com.example.destest.flowErrorCount
import com.example.destest.flowSuccessCount
import com.example.destest.resourceErrorIndex
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


class StoryRepositoryImplTest {

    private lateinit var storyRepositoryImpl: StoryRepositoryImpl

    private val contentApi = mockk<ContentApi>(relaxed = true)
    private val storyDao = mockk<StoryDao>(relaxed = true)

    private val storyId = 1

    @Before
    fun setup() {
        storyRepositoryImpl = StoryRepositoryImpl(
            contentApi,
            storyDao,
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getStory flow should start from ResourceLoading`() = runTest {
        // given

        // when
        val flow = storyRepositoryImpl.getStory(storyId)
        val data = flow.first()

        // then
        assert(data is Resource.Loading)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getStory flow should emit Resource 3 times`() = runTest {
        // given

        // when
        val flow = storyRepositoryImpl.getStory(storyId)
        val emits = flow.count()

        // then
        assert(emits == flowSuccessCount)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getStory should emit Resource Error on contentApi error`() = runTest {
        // given
        contentApi.apply { coEvery { getContent() } throws IOException() }

        // when
        val flow = storyRepositoryImpl.getStory(storyId)
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
    fun `getStory should call contentApi getContent`() = runTest {
        // given

        // when
        storyRepositoryImpl.getStory(storyId).toList()

        // then
        coVerify (exactly = 1) { contentApi.getContent() }
    }
}