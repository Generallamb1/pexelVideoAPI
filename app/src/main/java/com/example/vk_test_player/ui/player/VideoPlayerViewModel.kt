package com.example.vk_test_player.ui.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val player: ExoPlayer,
    private val savedInstance: SavedStateHandle
) : ViewModel() {

    init {
        playVideo()
    }

    fun getExoPlayer(): ExoPlayer {
        return player
    }

    fun playerStop(){
        player.stop()
    }

    private fun playVideo() {
        player.apply {
            val mediaItem = MediaItem.fromUri(savedInstance.get<String>("url").orEmpty())
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }
}