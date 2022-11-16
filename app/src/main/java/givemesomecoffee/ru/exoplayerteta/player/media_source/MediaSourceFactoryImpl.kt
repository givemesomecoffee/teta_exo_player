package givemesomecoffee.ru.exoplayerteta.player.media_source

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import givemesomecoffee.ru.exoplayerteta.player.error_handler.ErrorHandlingFactory
import givemesomecoffee.ru.exoplayerteta.player.logger.MediaSourceEventLogger

class MediaSourceFactoryImpl(
    private val context: Context,
    private val errorHandlingFactory: ErrorHandlingFactory
): MediaSourceFactory {
    override fun create(url: String): MediaSource {
        val dataSourceFactory = DefaultDataSource.Factory(context)
        val loadErrorHandlingPolicy = errorHandlingFactory.create()
        val mediaSourceFactory = DashMediaSource.Factory(dataSourceFactory).apply {
            setLoadErrorHandlingPolicy(loadErrorHandlingPolicy)
        }
        val mediaItem = MediaItem.fromUri(url)

        return mediaSourceFactory.createMediaSource(mediaItem).apply {
            addEventListener(Handler(Looper.getMainLooper()), MediaSourceEventLogger())
        }
    }
}