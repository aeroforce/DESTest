package com.example.destest.feature.player.data.repository

import com.example.destest.core.ErrorMessage
import com.example.destest.core.util.Resource
import com.example.destest.feature.content.data.local.VideoDao
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

class VideoRepositoryImplTest {
    private lateinit var videoRepositoryImpl: VideoRepositoryImpl

    private val contentApi = mockk<ContentApi>(relaxed = true)
    private val videoDao = mockk<VideoDao>(relaxed = true)

    private val videoId = 1

    @Before
    fun setup() {
        videoRepositoryImpl = VideoRepositoryImpl(
            contentApi,
            videoDao,
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getVideo flow should start from ResourceLoading`() = runTest {
        // given

        // when
        val flow = videoRepositoryImpl.getVideo(videoId)
        val data = flow.first()

        // then
        assert(data is Resource.Loading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getVideo flow should emit Resource 3 times`() = runTest {
        // given

        // when
        val flow = videoRepositoryImpl.getVideo(videoId)
        val emits = flow.count()

        // then
        assert(emits == flowSuccessCount)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getVideo should emit Resource Error on contentApi error`() = runTest {
        // given
        contentApi.apply { coEvery { getContent() } throws IOException() }

        // when
        val flow = videoRepositoryImpl.getVideo(videoId)
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
    fun `getVideo should call contentApi getContent`() = runTest {
        // given

        // when
        videoRepositoryImpl.getVideo(videoId).toList()

        // then
        coVerify(exactly = 1) { contentApi.getContent() }
    }
}
