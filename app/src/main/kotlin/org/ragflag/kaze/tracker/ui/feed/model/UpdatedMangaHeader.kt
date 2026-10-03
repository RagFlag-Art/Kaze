package org.ragflag.kaze.tracker.ui.feed.model

import org.ragflag.kaze.list.ui.ListModelDiffCallback
import org.ragflag.kaze.list.ui.model.ListModel
import org.ragflag.kaze.list.ui.model.MangaListModel

data class UpdatedMangaHeader(
	val list: List<MangaListModel>,
) : ListModel {

	override fun areItemsTheSame(other: ListModel): Boolean {
		return other is UpdatedMangaHeader
	}

	override fun getChangePayload(previousState: ListModel): Any {
		return ListModelDiffCallback.PAYLOAD_NESTED_LIST_CHANGED
	}
}
