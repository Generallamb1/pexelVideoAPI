package com.example.vk_test_player.domain.datamodels

data class VideoFiles(
    val id: Int,
    val quality: String,
    val fileType: String,
    val width: Int,
    val height: Int,
    val link: String
)
