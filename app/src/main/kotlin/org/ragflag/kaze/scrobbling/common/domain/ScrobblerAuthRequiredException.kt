package org.ragflag.kaze.scrobbling.common.domain

import okio.IOException
import org.ragflag.kaze.scrobbling.common.domain.model.ScrobblerService

class ScrobblerAuthRequiredException(
	val scrobbler: ScrobblerService,
) : IOException()
