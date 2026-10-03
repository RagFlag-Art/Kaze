package org.ragflag.kaze.core.ui.model

import org.ragflag.kaze.parsers.model.ContentRating

data class MangaOverride(
	val coverUrl: String?,
	val title: String?,
	val contentRating: ContentRating?,
)
