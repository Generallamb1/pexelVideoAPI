package com.example.vk_test_player.ui.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.vk_test_player.data.model.Video

@Composable
fun VideoPlayerListScreen(videoList: List<Video>){

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(200.dp),
        content = {
            item {

            }
        }
    )
}

@Composable
private fun VideoCard(video: Video) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
//        AsyncImage(model = , contentDescription = )
//        Text(text = video.)
    }
}