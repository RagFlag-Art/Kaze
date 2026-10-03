package org.ragflag.kaze.core.exceptions

import org.ragflag.kaze.core.model.UnknownMangaSource
import org.ragflag.kaze.parsers.model.MangaSource
import org.ragflag.kaze.parsers.network.CloudFlareHelper

class CloudFlareBlockedException(
	override val url: String,
	source: MangaSource?,
) : CloudFlareException("Blocked by CloudFlare", CloudFlareHelper.PROTECTION_BLOCKED) {

	override val source: MangaSource = source ?: UnknownMangaSource
}
