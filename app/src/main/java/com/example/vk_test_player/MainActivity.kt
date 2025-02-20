package com.example.vk_test_player

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil3.compose.AsyncImage
import com.example.vk_test_player.data.api.HttpHandler
import com.example.vk_test_player.data.model.Video
import com.example.vk_test_player.ui.list.VideoPlayerListScreen
import com.example.vk_test_player.ui.theme.VK_test_playerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VK_test_playerTheme {

//                val context = LocalContext.current
                val client = HttpHandler()
                val scope = rememberCoroutineScope()
                var videoList by remember { mutableStateOf<List<Video>>(emptyList()) }

                LaunchedEffect(true) {
                    scope.launch {
                        videoList = client.getSomeVideos()
                    }}
                //VideoPlayerListScreen(videoList = videoList)
//                val player = ExoPlayer.Builder(context).build()
//                val mediaItem = MediaItem.fromUri("https://videos.pexels.com/video-files/3571264/3571264-sd_960_540_30fps.mp4")
//                player.setMediaItem(mediaItem)
//                player.prepare()
//                player.play()


                val context = LocalContext.current
                val exoPlayer = remember {
                    ExoPlayer.Builder(context).build().apply {
                        val mediaItem = MediaItem.fromUri("https://videos.pexels.com/video-files/3571264/3571264-sd_960_540_30fps.mp4")
                        setMediaItem(mediaItem)
                        prepare()
                        playWhenReady = true
                    }
                }

                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            player = exoPlayer
                        }
                    }
                )

            }
        }
    }
}
