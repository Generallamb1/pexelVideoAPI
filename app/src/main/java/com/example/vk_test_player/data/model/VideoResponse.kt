package com.example.vk_test_player.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoResponse(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val image: String,
    val duration: Int,
    val user: UserResponse,
    val video_files: List<VideoFilesResponse>,
    val video_pictures: List<VideoPicturesResponse>
)
