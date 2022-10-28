package com.example.destest.feature.content.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.destest.feature.content.data.local.entity.StoryEntity

@Dao
interface StoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(stories: List<StoryEntity>)

    @Query("DELETE FROM StoryEntity WHERE id IN(:stories)")
    suspend fun deleteStories(stories: List<Int>)

    @Query("SELECT * FROM storyentity ORDER BY date DESC")
    suspend fun getStories(): List<StoryEntity>

    @Query("SELECT * FROM storyentity WHERE id LIKE '%' || :id || '%'")
    suspend fun getStory(id: Int): StoryEntity
}
