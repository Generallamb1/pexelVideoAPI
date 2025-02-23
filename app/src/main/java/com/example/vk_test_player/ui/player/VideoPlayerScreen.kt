package com.example.vk_test_player.ui.player

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController

@Composable
fun VideoPlayerScreen(navController: NavController) {
    val viewModel = hiltViewModel<VideoPlayerViewModel>()
    val exoPlayer = viewModel.getExoPlayer()
    var isPlayerVisible by remember { mutableStateOf(true) }

    BackHandler {
        viewModel.playerStop()
        isPlayerVisible = false
        navController.popBackStack()
    }


    Column() {
        IconButton(
            modifier = Modifier.statusBarsPadding(),
            onClick = {
                viewModel.playerStop()
                navController.popBackStack()
            }
        ) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Стрелка назад")
        }
        if (isPlayerVisible)
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    PlayerView(ctx).apply { player = exoPlayer }
                }
            )
    }
}
