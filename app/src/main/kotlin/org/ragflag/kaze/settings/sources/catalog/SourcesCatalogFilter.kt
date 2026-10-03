package org.ragflag.kaze.settings.sources.catalog

import org.ragflag.kaze.parsers.model.ContentType

data class SourcesCatalogFilter(
	val types: Set<ContentType>,
	val locale: String?,
	val isNewOnly: Boolean,
)
