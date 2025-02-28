package com.example.myrecipeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myrecipeapp.ui.theme.Brown
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubeVideoPlayer(url: String) {
    var isLoading by remember { mutableStateOf(true) }

    Box() {
        AndroidView(factory = {
            val view = YouTubePlayerView(it)
            view.addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        isLoading = false
                        val videoId = url.substringAfter("watch?v=").substringBefore("&")
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                }
            )
            view
        })

        if (isLoading) {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .matchParentSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center),
                    color = Brown
                )
            }

        }
    }
}