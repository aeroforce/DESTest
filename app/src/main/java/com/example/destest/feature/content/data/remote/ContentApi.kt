package com.example.destest.feature.content.data.remote

import com.example.destest.feature.content.data.remote.dto.ContentDto
import retrofit2.http.GET

interface ContentApi {

    @GET("/api/json-storage/bin/edfefba")
    suspend fun getContent(): ContentDto

    companion object {
        const val BASE_URL = "https://extendsclass.com"
    }
}
