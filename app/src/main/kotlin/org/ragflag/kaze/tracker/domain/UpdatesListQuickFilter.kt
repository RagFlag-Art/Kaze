package org.ragflag.kaze.tracker.domain

import org.ragflag.kaze.core.prefs.AppSettings
import org.ragflag.kaze.favourites.domain.FavouritesRepository
import org.ragflag.kaze.list.domain.ListFilterOption
import org.ragflag.kaze.list.domain.MangaListQuickFilter
import javax.inject.Inject

class UpdatesListQuickFilter @Inject constructor(
	private val favouritesRepository: FavouritesRepository,
	settings: AppSettings,
) : MangaListQuickFilter(settings) {

	override suspend fun getAvailableFilterOptions(): List<ListFilterOption> =
		favouritesRepository.getMostUpdatedCategories(
			limit = 4,
		).map {
			ListFilterOption.Favorite(it)
		}
}
