package org.ragflag.kaze.core.exceptions

import okio.IOException
import org.ragflag.kaze.parsers.model.MangaSource

abstract class CloudFlareException(
	message: String,
	val state: Int,
) : IOException(message) {

	abstract val url: String

	abstract val source: MangaSource
}
