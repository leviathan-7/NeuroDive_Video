package com.example.neurodive_video

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class Video : AppCompatActivity() {
    lateinit var youtubePlayerView: YouTubePlayerView

    // on below line we are creating a
    // string variable for our video id.
    var videoID = "vG2PNdI8axo"
    var videoID1 = "S0Q4gqBUs7c"
    var goToNext = false;
    private var ind = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        initMusicS()
        if (VideoPull.size < 1)
            initStartMusicPull()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video)

        youtubePlayerView = findViewById(R.id.youtube_player_view)

        lifecycle.addObserver(youtubePlayerView)

        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoID, 0f)
            }

            override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                if(state == PlayerConstants.PlayerState.ENDED)
                {
                    videoID = videoID1
                    onReady(youTubePlayer)
                }
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                if(goToNext){
                    goToNext = false
                    videoID = videoID1
                    onReady(youTubePlayer)
                }
            }
        })
    }

    fun toMenu(view: View){
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
    }
    fun next(view: View){

        goToNext = true
        changeMusic()
    }

    private fun initStartMusicPull()
    {
        VideoPull = mutableListOf<String>()
        VideoPull += "S0Q4gqBUs7c"
        for(key in MusicLists.keys)
            VideoPull += MusicLists[key] !!
        //VideoPull = MusicLists["Рок"]!!
    }

    private fun changeMusic(){
        ind += 1
        if (ind >= VideoPull.size)
            ind = 0
        videoID1 = VideoPull[ind]
    }

    private fun changeMusic2(){
        if (videoID ==  "S0Q4gqBUs7c")
            videoID1 = "vG2PNdI8axo"
        else
            videoID1 = "S0Q4gqBUs7c"
    }
}