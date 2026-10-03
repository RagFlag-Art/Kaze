package org.ragflag.kaze.core.exceptions

import okio.IOException
import org.ragflag.kaze.parsers.model.MangaSource

class InteractiveActionRequiredException(
	val source: MangaSource,
	val url: String,
) : IOException("Interactive action is required for ${source.name}")
