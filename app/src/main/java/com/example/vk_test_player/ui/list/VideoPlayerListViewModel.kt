package com.example.vk_test_player.ui.list

import androidx.lifecycle.ViewModel


class VideoPlayerListViewModel: ViewModel() {

    fun getVideoTitleFromUrl(link: String): String{
        return link.substringAfterLast(delimiter = "video/")
    }

}