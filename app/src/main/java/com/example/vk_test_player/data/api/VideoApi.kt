package com.example.vk_test_player.data.api

import com.example.vk_test_player.data.model.VideoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoApi @Inject constructor() {

    object Const {
        const val API_KEY = "b6tWIpBh4qIIQ1UHNkVPwXohkWgZQS8UGMJYZkq3N48nUPGfDjSDWoEj"
        const val GET_VIDEO_PATH = "https://api.pexels.com/videos/search?query=nature&per_page=30"
    }

    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun getSomeVideos(): List<VideoResponse> {
        val response: JsonVideoResponse =
            client.get(Const.GET_VIDEO_PATH) {
                headers {
                    headers.append("Authorization", Const.API_KEY)
                }
            }.body()
        return response.videos
    }

}

@Serializable
data class JsonVideoResponse(
    val videos: List<VideoResponse>
)
