package givemesomecoffee.ru.exoplayerteta.player

import com.google.android.exoplayer2.ExoPlayer

interface PlayerFactory {
    fun create(streamUrl: String): ExoPlayer
}