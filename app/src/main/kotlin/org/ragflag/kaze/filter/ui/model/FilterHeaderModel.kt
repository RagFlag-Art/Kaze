package org.ragflag.kaze.filter.ui.model

import org.ragflag.kaze.core.ui.widgets.ChipsView
import org.ragflag.kaze.parsers.model.SortOrder

data class FilterHeaderModel(
	val chips: Collection<ChipsView.ChipModel>,
	val sortOrder: SortOrder?,
	val isFilterApplied: Boolean,
) {

	val textSummary: String
		get() = chips.mapNotNull { if (it.isChecked) it.title else null }.joinToString()
}
