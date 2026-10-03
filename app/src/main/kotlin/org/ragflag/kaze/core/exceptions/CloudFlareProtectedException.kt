package org.ragflag.kaze.core.exceptions

import okhttp3.Headers
import org.ragflag.kaze.core.model.UnknownMangaSource
import org.ragflag.kaze.parsers.model.MangaSource
import org.ragflag.kaze.parsers.network.CloudFlareHelper

class CloudFlareProtectedException(
	override val url: String,
	source: MangaSource?,
	@Transient val headers: Headers,
) : CloudFlareException("Protected by CloudFlare", CloudFlareHelper.PROTECTION_CAPTCHA) {

	override val source: MangaSource = source ?: UnknownMangaSource
}
