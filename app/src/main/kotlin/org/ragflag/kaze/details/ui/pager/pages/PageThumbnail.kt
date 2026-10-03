package org.ragflag.kaze.details.ui.pager.pages

import org.ragflag.kaze.list.ui.model.ListModel
import org.ragflag.kaze.reader.ui.pager.ReaderPage

data class PageThumbnail(
	val isCurrent: Boolean,
	val page: ReaderPage,
) : ListModel {

	val number
		get() = page.index + 1

	override fun areItemsTheSame(other: ListModel): Boolean {
		return other is PageThumbnail && page == other.page
	}
}
