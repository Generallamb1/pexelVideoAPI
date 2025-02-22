package com.example.vk_test_player.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int,
    val name: String,
    val url: String
)
