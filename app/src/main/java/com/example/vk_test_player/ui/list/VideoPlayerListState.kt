package com.example.vk_test_player.ui.list

import com.example.vk_test_player.domain.datamodels.Video

data class VideoPlayerListState(
    val videoList : List<Video> = emptyList(),
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false
)
