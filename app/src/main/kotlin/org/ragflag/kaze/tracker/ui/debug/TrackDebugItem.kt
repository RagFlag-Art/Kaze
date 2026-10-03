package org.ragflag.kaze.tracker.ui.debug

import org.ragflag.kaze.list.ui.model.ListModel
import org.ragflag.kaze.parsers.model.Manga
import java.time.Instant

data class TrackDebugItem(
	val manga: Manga,
	val lastChapterId: Long,
	val newChapters: Int,
	val lastCheckTime: Instant?,
	val lastChapterDate: Instant?,
	val lastResult: Int,
	val lastError: String?,
) : ListModel {

	override fun areItemsTheSame(other: ListModel): Boolean {
		return other is TrackDebugItem && other.manga.id == manga.id
	}
}
