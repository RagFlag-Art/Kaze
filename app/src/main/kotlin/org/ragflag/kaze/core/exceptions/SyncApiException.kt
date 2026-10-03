package org.ragflag.kaze.core.exceptions

class SyncApiException(
	message: String,
	val code: Int,
) : RuntimeException(message)
