package com.example.vk_test_player

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.vk_test_player.data.api.HttpHandler
import com.example.vk_test_player.data.model.Video
import com.example.vk_test_player.ui.theme.VK_test_playerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VK_test_playerTheme {
                val client = HttpHandler()
                val scope = rememberCoroutineScope()
                var videoList by remember { mutableStateOf<List<Video>>(emptyList()) }

                LaunchedEffect(true) {
                    scope.launch {
                        videoList = client.getSomeVideos()
                    }}

                Column(
                    modifier = Modifier.padding(top = 30.dp)
                ) {
                    Text(
                        text = "Видео про природу",
                        textAlign = TextAlign.Left,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier.padding(top = 20.dp),
                        columns = StaggeredGridCells.Adaptive(200.dp),
                        content = {
                            videoList.forEach{
                                item {
                                    VideoCard(
                                        it.image,
                                        it.url
                                            .substringAfterLast("video/")
                                            .replace(Regex("[^a-zA-Z]"), " ")
                                            .replaceFirstChar { it.uppercase() }
                                            .trim()
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun VideoCard(imageUrl: String, videoTitle: String) {
    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 10.dp, bottom = 20.dp)
            .clickable {

            }
    ) {
        AsyncImage(model = imageUrl, contentDescription = "Изображение для видео")
        Text(
            text = videoTitle,
            fontSize = 20.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(5.dp)
        )
    }
}
