package givemesomecoffee.ru.exoplayerteta.player.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoMeta(
    val playWhenReady: Boolean = true,
    val currentItem: Int = 0,
    val playbackPosition: Long = 0L,
) : Parcelable