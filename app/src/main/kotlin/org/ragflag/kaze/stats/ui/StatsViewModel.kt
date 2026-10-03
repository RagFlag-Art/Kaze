package org.ragflag.kaze.stats.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.take
import org.ragflag.kaze.R
import org.ragflag.kaze.core.model.FavouriteCategory
import org.ragflag.kaze.core.ui.BaseViewModel
import org.ragflag.kaze.core.ui.util.ReversibleAction
import org.ragflag.kaze.core.util.ext.MutableEventFlow
import org.ragflag.kaze.core.util.ext.call
import org.ragflag.kaze.favourites.domain.FavouritesRepository
import org.ragflag.kaze.stats.data.StatsRepository
import org.ragflag.kaze.stats.domain.StatsPeriod
import org.ragflag.kaze.stats.domain.StatsRecord
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
	private val repository: StatsRepository,
	favouritesRepository: FavouritesRepository,
) : BaseViewModel() {

	val period = MutableStateFlow(StatsPeriod.WEEK)
	val onActionDone = MutableEventFlow<ReversibleAction>()
	val selectedCategories = MutableStateFlow<Set<Long>>(emptySet())
	val favoriteCategories = favouritesRepository.observeCategories()
		.take(1)

	val readingStats = MutableStateFlow<List<StatsRecord>>(emptyList())

	init {
		launchJob(Dispatchers.Default) {
			combine<StatsPeriod, Set<Long>, Pair<StatsPeriod, Set<Long>>>(
				period,
				selectedCategories,
				::Pair,
			).collectLatest { p ->
				readingStats.value = withLoading {
					repository.getReadingStats(p.first, p.second)
				}
			}
		}
	}

	fun setCategoryChecked(category: FavouriteCategory, checked: Boolean) {
		val snapshot = selectedCategories.value.toMutableSet()
		if (checked) {
			snapshot.add(category.id)
		} else {
			snapshot.remove(category.id)
		}
		selectedCategories.value = snapshot
	}

	fun clearStats() {
		launchLoadingJob(Dispatchers.Default) {
			repository.clearStats()
			readingStats.value = emptyList()
			onActionDone.call(ReversibleAction(R.string.stats_cleared, null))
		}
	}
}
