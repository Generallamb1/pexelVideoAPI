package com.example.vk_test_player.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.vk_test_player.data.model.Video

@Composable
fun VideoPlayerListScreen(videoList: List<Video>){

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

@Composable
fun VideoCard(imageUrl: String, videoTitle: String) {
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


