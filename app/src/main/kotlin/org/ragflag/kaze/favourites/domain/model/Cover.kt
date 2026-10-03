package org.ragflag.kaze.favourites.domain.model

import org.ragflag.kaze.core.model.MangaSource

data class Cover(
	val url: String?,
	val source: String,
) {
	val mangaSource by lazy { MangaSource(source) }
}
