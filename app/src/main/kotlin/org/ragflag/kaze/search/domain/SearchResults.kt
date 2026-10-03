package org.ragflag.kaze.search.domain

import org.ragflag.kaze.parsers.model.Manga
import org.ragflag.kaze.parsers.model.MangaListFilter
import org.ragflag.kaze.parsers.model.SortOrder

data class SearchResults(
	val listFilter: MangaListFilter,
	val sortOrder: SortOrder,
	val manga: List<Manga>,
)
