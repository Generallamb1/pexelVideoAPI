package com.example.vk_test_player.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoFiles(
    val id: Int,
    val quality: String?,
    val file_type: String,
    val width: Int?,
    val height: Int?,
    val fps: Double?,
    val link: String?
)
