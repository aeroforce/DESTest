package com.example.destest.feature.content.domain.model

import java.sql.Date

sealed class ContentItem {
    abstract val id: Int
    abstract val date: Double
    abstract val title: String
    abstract val sport: String
}

fun ContentItem.getDate() = "${Date(date.times(1000).toLong())}"
