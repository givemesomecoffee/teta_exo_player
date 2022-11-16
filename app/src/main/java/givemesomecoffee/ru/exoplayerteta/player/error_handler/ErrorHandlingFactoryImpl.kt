package givemesomecoffee.ru.exoplayerteta.player.error_handler

import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy

class ErrorHandlingFactoryImpl: ErrorHandlingFactory {
    override fun create(): LoadErrorHandlingPolicy {
        return object : DefaultLoadErrorHandlingPolicy() {
            override fun getRetryDelayMsFor(loadErrorInfo: LoadErrorHandlingPolicy.LoadErrorInfo): Long {
                return if (loadErrorInfo.errorCount >= 3) {
                    C.TIME_UNSET
                } else {
                    (loadErrorInfo.errorCount * 2000).toLong()
                }
            }

            override fun getMinimumLoadableRetryCount(dataType: Int): Int {
                return 3
            }
        }

    }
}