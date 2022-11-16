package givemesomecoffee.ru.exoplayerteta.player.media_source

import com.google.android.exoplayer2.source.MediaSource

interface MediaSourceFactory {
    fun create(url: String): MediaSource
}