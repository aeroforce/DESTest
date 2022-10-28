package com.example.destest.feature.content.domain.model

data class Video(
    override val id: Int,
    override val title: String,
    override val date: Double,
    override val sport: String,
    val thumb: String,
    val url: String,
    val views: Int
) : ContentItem() {
    companion object {
        val Empty = Video(0, "", 0.0, "", "", "", 0)
    }
}
