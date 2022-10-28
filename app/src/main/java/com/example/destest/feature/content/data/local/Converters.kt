package com.example.destest.feature.content.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.destest.feature.content.data.util.JsonParser
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromSportJson(json: String): String {
        return jsonParser.fromJson<String>(
            json,
            object : TypeToken<String>() {}.type
        ) ?: ""
    }

    @TypeConverter
    fun toSportJson(sport: String): String {
        return jsonParser.toJson<String>(
            sport,
            object : TypeToken<String>() {}.type
        ) ?: "[]"
    }
}
