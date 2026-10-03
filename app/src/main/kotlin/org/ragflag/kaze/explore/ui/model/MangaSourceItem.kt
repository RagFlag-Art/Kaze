package org.ragflag.kaze.explore.ui.model

import org.ragflag.kaze.core.model.MangaSourceInfo
import org.ragflag.kaze.list.ui.model.ListModel
import org.ragflag.kaze.parsers.util.longHashCode

data class MangaSourceItem(
	val source: MangaSourceInfo,
	val isGrid: Boolean,
) : ListModel {

	val id: Long = source.name.longHashCode()

	override fun areItemsTheSame(other: ListModel): Boolean {
		return other is MangaSourceItem && other.source == source
	}
}
