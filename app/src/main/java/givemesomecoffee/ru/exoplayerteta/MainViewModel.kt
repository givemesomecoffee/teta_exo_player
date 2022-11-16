package givemesomecoffee.ru.exoplayerteta

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import givemesomecoffee.ru.exoplayerteta.player.model.VideoMeta

class MainViewModel(private val state: SavedStateHandle): ViewModel() {
    fun getVideoMeta(): VideoMeta {
        return state.get<VideoMeta>(VIDEO_META) ?: VideoMeta()
    }

    fun saveVideoMeta(
        playWhenReady: Boolean,
        currentItem: Int,
        playbackPosition: Long,
    ) {
        state[VIDEO_META] = VideoMeta(playWhenReady, currentItem, playbackPosition)
    }

    companion object {
        const val VIDEO_META = "video_meta"
    }
}