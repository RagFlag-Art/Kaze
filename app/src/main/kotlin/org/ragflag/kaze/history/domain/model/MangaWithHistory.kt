package org.ragflag.kaze.history.domain.model

import org.ragflag.kaze.core.model.MangaHistory
import org.ragflag.kaze.parsers.model.Manga

data class MangaWithHistory(
	val manga: Manga,
	val history: MangaHistory
)
