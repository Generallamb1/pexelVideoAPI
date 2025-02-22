package com.example.vk_test_player.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_test_player.data.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VideoPlayerListViewModel @Inject constructor(
    private val repository: VideoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VideoPlayerListState())
    val uiState: StateFlow<VideoPlayerListState> = _uiState.asStateFlow()

    init {
        loadNewVideos()
    }

    fun loadNewVideos(isRefreshing: Boolean = false) {
        viewModelScope.launch {
            _uiState.emit(
                _uiState.value.copy(
                    isLoading = !isRefreshing,
                    isRefreshing = isRefreshing
                )
            )
            val videos = repository.getVideosFromApi().shuffled()
            _uiState.emit(
                _uiState.value.copy(
                    videoList = videos,
                    isLoading = false,
                    isRefreshing = false
                )
            )
        }
    }
}
