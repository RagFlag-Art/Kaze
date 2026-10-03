package org.ragflag.kaze.tracker.domain.model

import org.ragflag.kaze.parsers.exception.TooManyRequestExceptions
import org.ragflag.kaze.parsers.model.Manga
import org.ragflag.kaze.parsers.model.MangaChapter
import org.ragflag.kaze.parsers.util.ifZero

sealed interface MangaUpdates {

	val manga: Manga

	data class Success(
		override val manga: Manga,
		val branch: String?,
		val newChapters: List<MangaChapter>,
		val isValid: Boolean,
	) : MangaUpdates {

		fun isNotEmpty() = newChapters.isNotEmpty()

		fun lastChapterDate(): Long {
			val lastChapter = newChapters.lastOrNull()
			return lastChapter?.uploadDate?.ifZero { System.currentTimeMillis() }
				?: (manga.chapters?.lastOrNull()?.uploadDate ?: 0L)
		}
	}

	data class Failure(
		override val manga: Manga,
		val error: Throwable?,
	) : MangaUpdates {

		fun shouldRetry() = error is TooManyRequestExceptions
	}
}
