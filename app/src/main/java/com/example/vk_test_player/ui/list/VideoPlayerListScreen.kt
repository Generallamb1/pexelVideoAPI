package com.example.vk_test_player.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.vk_test_player.domain.datamodels.Video
import com.example.vk_test_player.navigation.VideoPlayerScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPlayerListScreen(navController: NavController) {

    val viewModel = hiltViewModel<VideoPlayerListViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        isRefreshing = uiState.isRefreshing,
        onRefresh = {
            viewModel.loadNewVideos(isRefreshing = true)
        }
    ) {
        if (uiState.isLoading) {
            loader()
            return@PullToRefreshBox
        }
        Column(
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Text(
                text = "Видео про природу",
                textAlign = TextAlign.Left,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            LazyVerticalStaggeredGrid(
                modifier = Modifier.padding(top = 20.dp),
                columns = StaggeredGridCells.Fixed(2),
                content = {
                    uiState.videoList.forEach() {
                        item {
                            VideoCard(
                                it,
                                navController = navController
                            )
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun loader() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(64.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    }
}

@Composable
fun VideoCard(
    video: Video,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .padding(start = 20.dp, end = 10.dp, bottom = 20.dp)
            .clickable {
                navController.navigate(
                    VideoPlayerScreenRoute(
                        url = video.videoFiles.find { it.quality == "hd" }?.link.orEmpty()
                    )
                )
            }

    ) {
        AsyncImage(
            model = video.image,
            contentDescription = "Изображение для видео",
            contentScale = ContentScale.Crop
        )
        Text(
            text = video.videoName,
            fontSize = 20.sp,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = video.duration.toString(),
            fontSize = 20.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(5.dp)
        )
    }
}
