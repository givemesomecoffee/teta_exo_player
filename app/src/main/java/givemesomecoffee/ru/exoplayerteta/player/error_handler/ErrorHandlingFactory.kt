package givemesomecoffee.ru.exoplayerteta.player.error_handler

import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy

interface ErrorHandlingFactory {
    fun create(): LoadErrorHandlingPolicy
}