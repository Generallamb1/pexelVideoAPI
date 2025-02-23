package com.example.vk_test_player.ui.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController

@Composable
fun VideoPlayerScreen(navController: NavController) {
    val viewModel = hiltViewModel<VideoPlayerViewModel>()
    val exoPlayer = viewModel.getExoPlayer()

    Column() {
        IconButton(
            modifier = Modifier.statusBarsPadding(),
            onClick = {
                navController.popBackStack()
                viewModel.playerStop()
            }
        ) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Стрелка назад")
        }
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                PlayerView(ctx).apply { player = exoPlayer }
            }
        )
    }
}
