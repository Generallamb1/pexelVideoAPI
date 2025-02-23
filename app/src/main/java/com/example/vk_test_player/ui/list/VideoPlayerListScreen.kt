package com.example.vk_test_player.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.vk_test_player.domain.datamodels.Video
import com.example.vk_test_player.navigation.VideoPlayerScreenRoute
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

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

                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxSize(),
                columns = StaggeredGridCells.Fixed(if (isPortrait()) 2 else 4),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalItemSpacing = 16.dp,
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

//@Composable
//fun VideoCard(
//    video: Video,
//    navController: NavController
//) {
//    Card(
//        modifier = Modifier
//            .height(if (video.height > video.width) 400.dp else 150.dp)
//            .clickable {
//                navController.navigate(
//                    VideoPlayerScreenRoute(
//                        url = video.videoFiles.find { it.quality == "hd" }?.link.orEmpty()
//                    )
//                )
//            }
//
//    ) {
//        Column() {
//            AsyncImage(
//                model = video.image,
//                contentDescription = "Изображение для видео",
//                modifier = Modifier.height(if (video.height > video.width) 300.dp else 50.dp),
//                contentScale = ContentScale.Crop
//            )
//            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
//                Text(
//                    text = video.videoName,
//                    fontSize = 20.sp,
//                    textAlign = TextAlign.Left,
//                    fontWeight = FontWeight.Black,
//                    modifier = Modifier.padding(5.dp)
//                )
//                Text(
//                    text = video.duration,
//                    fontSize = 20.sp,
//                    textAlign = TextAlign.Left,
//                    modifier = Modifier.padding(5.dp)
//                )
//            }
//        }
//    }
//}

@Composable
fun VideoCard(
    video: Video,
    navController: NavController
) {
    BoxWithConstraints {
        val isPortrait = video.height > video.width
        val cardHeight = if (isPortrait) maxHeight * 0.5f else maxHeight * 0.25f
        val imageHeight = if (isPortrait) cardHeight * 0.75f else cardHeight * 0.5f

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(cardHeight)
                .clickable {
                    navController.navigate(
                        VideoPlayerScreenRoute(
                            url = video.videoFiles.find { it.quality == "hd" }?.link.orEmpty()
                        )
                    )
                }
        ) {
            Column {
                AsyncImage(
                    model = video.image,
                    contentDescription = "Изображение для видео",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(imageHeight),
                    contentScale = ContentScale.Fit
                )
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = video.videoName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = video.duration,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


@Composable
fun isPortrait(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT
}