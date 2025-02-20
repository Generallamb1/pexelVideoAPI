package com.example.vk_test_player.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoPictures(
    val id: Int,
    val picture: String,
    val nr: Int
)
