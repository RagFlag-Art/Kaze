package org.ragflag.kaze.suggestions.domain

import androidx.annotation.FloatRange
import org.ragflag.kaze.parsers.model.Manga

data class MangaSuggestion(
	val manga: Manga,
	@FloatRange(from = 0.0, to = 1.0)
	val relevance: Float,
)