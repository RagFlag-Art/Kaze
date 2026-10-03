package org.ragflag.kaze.favourites.domain

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import org.ragflag.kaze.core.db.MangaDatabase
import org.ragflag.kaze.core.db.entity.toManga
import org.ragflag.kaze.core.db.entity.toMangaTags
import org.ragflag.kaze.favourites.data.FavouriteManga
import org.ragflag.kaze.list.domain.ListFilterOption
import org.ragflag.kaze.list.domain.ListSortOrder
import org.ragflag.kaze.local.data.index.LocalMangaIndex
import org.ragflag.kaze.local.domain.LocalObserveMapper
import org.ragflag.kaze.parsers.model.Manga
import javax.inject.Inject

@Reusable
class LocalFavoritesObserver @Inject constructor(
	localMangaIndex: LocalMangaIndex,
	private val db: MangaDatabase,
) : LocalObserveMapper<FavouriteManga, Manga>(localMangaIndex) {

	fun observeAll(
		order: ListSortOrder,
		filterOptions: Set<ListFilterOption>,
		limit: Int
	): Flow<List<Manga>> = db.getFavouritesDao().observeAll(order, filterOptions, limit).mapToLocal()

	fun observeAll(
		categoryId: Long,
		order: ListSortOrder,
		filterOptions: Set<ListFilterOption>,
		limit: Int
	): Flow<List<Manga>> = db.getFavouritesDao().observeAll(categoryId, order, filterOptions, limit).mapToLocal()

	override fun toManga(e: FavouriteManga) = e.manga.toManga(e.tags.toMangaTags(), null)

	override fun toResult(e: FavouriteManga, manga: Manga) = manga
}
