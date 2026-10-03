package org.ragflag.kaze.settings.sources.catalog

import org.ragflag.kaze.list.ui.ListModelDiffCallback
import org.ragflag.kaze.list.ui.model.ListModel
import org.ragflag.kaze.parsers.model.ContentType

data class SourceCatalogPage(
	val type: ContentType,
	val items: List<SourceCatalogItem>,
) : ListModel {

	override fun areItemsTheSame(other: ListModel): Boolean {
		return other is SourceCatalogPage && other.type == type
	}

	override fun getChangePayload(previousState: ListModel): Any {
		return ListModelDiffCallback.PAYLOAD_NESTED_LIST_CHANGED
	}
}
