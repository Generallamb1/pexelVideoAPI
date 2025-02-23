package com.example.vk_test_player.domain.datamodels

data class Video(
    val id: Int,
    val width: Int,
    val height: Int,
    val image: String,
    val duration: String,
    val videoName: String,
    val videoFiles: List<VideoFiles>,
)
