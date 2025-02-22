package com.example.vk_test_player.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vk_test_player.ui.list.VideoPlayerListScreen
import com.example.vk_test_player.ui.player.VideoPlayerScreen
import kotlinx.serialization.Serializable


@Composable
fun NavController(navController: NavHostController): NavController {

    NavHost(navController = navController, startDestination = VideoPlayerListScreenRoute) {
        composable<VideoPlayerScreenRoute> {
            VideoPlayerScreen(navController = navController)
        }
        composable<VideoPlayerListScreenRoute> {
            VideoPlayerListScreen(navController = navController)
        }
    }
    return navController
}

@Serializable
object VideoPlayerListScreenRoute

@Serializable
data class VideoPlayerScreenRoute(
    val url: String
)