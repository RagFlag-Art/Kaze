package org.ragflag.kaze.core.exceptions

import org.ragflag.kaze.parsers.model.Manga

class UnsupportedSourceException(
	message: String?,
	val manga: Manga?,
) : IllegalArgumentException(message)
