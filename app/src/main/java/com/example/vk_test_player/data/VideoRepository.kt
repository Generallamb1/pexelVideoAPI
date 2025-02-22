package com.example.vk_test_player.data


import com.example.vk_test_player.data.api.VideoApi
import com.example.vk_test_player.domain.datamodels.Video
import com.example.vk_test_player.domain.datamodels.VideoFiles
import com.example.vk_test_player.data.model.VideoResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class VideoRepository @Inject constructor(
    val videoApi: VideoApi
) {

    fun mapVideo(videoResponse: VideoResponse): Video {
        return Video(
            id = videoResponse.id,
            width = videoResponse.width,
            height = videoResponse.height,
            image = videoResponse.image,
            duration = videoResponse.duration,
            videoName = videoResponse.url
                .substringAfterLast("video/")
                .replace(Regex("[^a-zA-Z]"), " ")
                .replaceFirstChar { it.uppercase() }
                .trim(),
            videoFiles = videoResponse.video_files.map {
                VideoFiles(
                    id = it.id,
                    quality = it.quality.orEmpty(),
                    fileType = it.file_type,
                    width = it.width ?: 0,
                    height = it.height ?: 0,
                    link = it.link.toString()
                )
            }
        )
    }

    suspend fun getVideosFromApi(): List<Video>{
        return videoApi.getSomeVideos().map {
            mapVideo(it)
        }
    }

}