package givemesomecoffee.ru.exoplayerteta.player.logger

import android.util.Log
import com.google.android.exoplayer2.source.LoadEventInfo
import com.google.android.exoplayer2.source.MediaLoadData
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MediaSourceEventListener
import java.io.IOException

class MediaSourceEventLogger: MediaSourceEventListener {

    override fun onLoadStarted(
        windowIndex: Int,
        mediaPeriodId: MediaSource.MediaPeriodId?,
        loadEventInfo: LoadEventInfo,
        mediaLoadData: MediaLoadData
    ) {
        Log.d(TAG, loadEventInfo.dataSpec.uri.toString() + " load start")
        super.onLoadStarted(windowIndex, mediaPeriodId, loadEventInfo, mediaLoadData)
    }

    override fun onLoadCompleted(
        windowIndex: Int,
        mediaPeriodId: MediaSource.MediaPeriodId?,
        loadEventInfo: LoadEventInfo,
        mediaLoadData: MediaLoadData
    ) {
        Log.d(TAG, loadEventInfo.dataSpec.uri.toString() + " load completed")
        super.onLoadCompleted(windowIndex, mediaPeriodId, loadEventInfo, mediaLoadData)
    }

    override fun onLoadError(
        windowIndex: Int,
        mediaPeriodId: MediaSource.MediaPeriodId?,
        loadEventInfo: LoadEventInfo,
        mediaLoadData: MediaLoadData,
        error: IOException,
        wasCanceled: Boolean
    ) {
        Log.e(TAG, loadEventInfo.dataSpec.uri.toString() + " load failed: " + error.message.toString())
        super.onLoadError(
            windowIndex,
            mediaPeriodId,
            loadEventInfo,
            mediaLoadData,
            error,
            wasCanceled
        )
    }
    companion object{
        const val TAG = "MediaSourceEventLogger"
    }
}