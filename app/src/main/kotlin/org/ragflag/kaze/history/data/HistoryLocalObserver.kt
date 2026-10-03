package org.ragflag.kaze.history.data

import dagger.Reusable
import org.ragflag.kaze.core.db.MangaDatabase
import org.ragflag.kaze.core.db.entity.toManga
import org.ragflag.kaze.core.db.entity.toMangaTags
import org.ragflag.kaze.history.domain.model.MangaWithHistory
import org.ragflag.kaze.list.domain.ListFilterOption
import org.ragflag.kaze.list.domain.ListSortOrder
import org.ragflag.kaze.local.data.index.LocalMangaIndex
import org.ragflag.kaze.local.domain.LocalObserveMapper
import org.ragflag.kaze.parsers.model.Manga
import javax.inject.Inject

@Reusable
class HistoryLocalObserver @Inject constructor(
	localMangaIndex: LocalMangaIndex,
	private val db: MangaDatabase,
) : LocalObserveMapper<HistoryWithManga, MangaWithHistory>(localMangaIndex) {

	fun observeAll(
		order: ListSortOrder,
		filterOptions: Set<ListFilterOption>,
		limit: Int
	) = db.getHistoryDao().observeAll(order, filterOptions, limit).mapToLocal()

	override fun toManga(e: HistoryWithManga) = e.manga.toManga(e.tags.toMangaTags(), null)

	override fun toResult(e: HistoryWithManga, manga: Manga) = MangaWithHistory(
		manga = manga,
		history = e.history.toMangaHistory(),
	)
}
