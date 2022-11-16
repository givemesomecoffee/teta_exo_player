package givemesomecoffee.ru.exoplayerteta

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.util.Util
import givemesomecoffee.ru.exoplayerteta.player.PlayerFactoryImpl
import givemesomecoffee.ru.exoplayerteta.player.error_handler.ErrorHandlingFactoryImpl
import givemesomecoffee.ru.exoplayerteta.player.media_source.MediaSourceFactoryImpl

class MainActivity : AppCompatActivity() {
    private var player: ExoPlayer? = null
    private val factory by lazy {
        PlayerFactoryImpl(this, MediaSourceFactoryImpl(this, ErrorHandlingFactoryImpl()))
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer()
        }
    }

    public override fun onPause() {
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
        super.onPause()
    }

    public override fun onStop() {
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
        super.onStop()
    }

    private fun initializePlayer() {
        player = factory.create(STREAM_URL).also { exoPlayer ->
            findViewById<StyledPlayerView>(R.id.player).player = exoPlayer
            val videoMeta = viewModel.getVideoMeta()
            exoPlayer.playWhenReady = videoMeta.playWhenReady
            exoPlayer.seekTo(videoMeta.currentItem, videoMeta.playbackPosition)
            exoPlayer.prepare()
        }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            viewModel.saveVideoMeta(
                playWhenReady = exoPlayer.playWhenReady,
                playbackPosition = (exoPlayer.currentPosition - 1000L).coerceAtLeast(0),
                currentItem = exoPlayer.currentMediaItemIndex
            )
            exoPlayer.release()
        }
        player = null
    }

    companion object {
        const val STREAM_URL =
            "https://bitmovin-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd"
    }
}