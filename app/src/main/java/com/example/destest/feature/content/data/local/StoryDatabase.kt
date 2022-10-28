package com.example.destest.feature.content.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.destest.feature.content.data.local.entity.StoryEntity

@Database(
    entities = [StoryEntity::class],
    version = 1,
)
// @TypeConverters(Converters::class)
abstract class StoryDatabase : RoomDatabase() {
    abstract val dao: StoryDao
}
