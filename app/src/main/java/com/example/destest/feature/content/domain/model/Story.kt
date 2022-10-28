package com.example.destest.feature.content.domain.model

data class Story(
    override val id: Int,
    override val title: String,
    override val date: Double,
    override val sport: String,
    val author: String,
    val image: String,
    val teaser: String,
) : ContentItem() {
    companion object {
        val Empty = Story(0, "", 0.0, "", "", "", "")
    }
}
