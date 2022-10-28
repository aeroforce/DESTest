package com.example.destest.feature.content.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.destest.feature.content.data.local.entity.VideoEntity

@Dao
interface VideoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(stories: List<VideoEntity>)

    @Query("DELETE FROM videoentity WHERE id IN(:videos)")
    suspend fun deleteVideos(videos: List<Int>)

    @Query("SELECT * FROM videoentity WHERE id LIKE '%' || :id || '%'")
    suspend fun getVideo(id: Int): VideoEntity

    @Query("SELECT * FROM videoentity ORDER BY date DESC")
    suspend fun getVideos(): List<VideoEntity>
}
