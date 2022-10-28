package com.example.destest.feature.content.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.destest.feature.content.data.local.entity.VideoEntity

@Database(
    entities = [VideoEntity::class],
    version = 1,
)
// @TypeConverters(Converters::class)
abstract class VideoDatabase : RoomDatabase() {
    abstract val dao: VideoDao
}
