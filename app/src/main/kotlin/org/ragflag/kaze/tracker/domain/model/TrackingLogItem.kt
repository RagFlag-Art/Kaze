package org.ragflag.kaze.tracker.domain.model

import org.ragflag.kaze.parsers.model.Manga
import java.time.Instant

data class TrackingLogItem(
	val id: Long,
	val manga: Manga,
	val chapters: List<String>,
	val createdAt: Instant,
	val isNew: Boolean,
)
