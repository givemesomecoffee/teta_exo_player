package givemesomecoffee.ru.exoplayerteta.player

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import givemesomecoffee.ru.exoplayerteta.player.media_source.MediaSourceFactory

class PlayerFactoryImpl(
    private val context: Context,
    private val mediaSourceFactory: MediaSourceFactory
) : PlayerFactory {
    override fun create(streamUrl: String): ExoPlayer {
        val mediaSource = mediaSourceFactory.create(streamUrl)
        return ExoPlayer.Builder(context)
            .build()
            .apply {
                addMediaSource(mediaSource)
            }
    }
}