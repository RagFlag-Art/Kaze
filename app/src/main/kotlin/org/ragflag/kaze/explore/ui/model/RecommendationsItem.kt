package org.ragflag.kaze.explore.ui.model

import org.ragflag.kaze.list.ui.model.ListModel
import org.ragflag.kaze.list.ui.model.MangaCompactListModel

data class RecommendationsItem(
	val manga: List<MangaCompactListModel>
) : ListModel {

	override fun areItemsTheSame(other: ListModel): Boolean {
		return other is RecommendationsItem
	}
}
