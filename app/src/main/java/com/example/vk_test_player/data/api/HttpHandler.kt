package com.example.vk_test_player.data.api

import android.hardware.camera2.CameraExtensionSession.StillCaptureLatency
import com.example.vk_test_player.data.model.Video
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsBytes
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class HttpHandler {

    object Const {
        const val API_KEY = "b6tWIpBh4qIIQ1UHNkVPwXohkWgZQS8UGMJYZkq3N48nUPGfDjSDWoEj"
    }

    private val client = HttpClient(OkHttp){
        install(ContentNegotiation){
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }


    suspend fun getVideo(): Video {
        val response: Video = client.get( "https://api.pexels.com/videos/videos/28972885"){
            headers{
                headers.append("Authorization", Const.API_KEY)
            }
        }.body()
        return response
    }

    suspend fun getSomeVideos(): List<Video>{
        val response: VideoResponse = client.get( "https://api.pexels.com/videos/search?query=nature&per_page=30"){
            headers{
                headers.append("Authorization", Const.API_KEY)
            }
        }.body()
        return response.videos
    }

}

@Serializable
data class VideoResponse(
    val videos: List<Video>
)