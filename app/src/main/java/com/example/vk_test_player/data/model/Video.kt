package com.example.vk_test_player.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val image: String,
    val duration: Int,
    val user: User,
    val video_files: Array<VideoFiles>,
    val video_pictures: Array<VideoPictures>
)
