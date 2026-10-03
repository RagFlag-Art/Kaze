package org.ragflag.kaze.scrobbling.common.domain

import org.ragflag.kaze.scrobbling.anilist.data.AniListRepository
import org.ragflag.kaze.scrobbling.common.data.ScrobblerRepository
import org.ragflag.kaze.scrobbling.common.domain.model.ScrobblerService
import org.ragflag.kaze.scrobbling.kitsu.data.KitsuRepository
import org.ragflag.kaze.scrobbling.mal.data.MALRepository
import org.ragflag.kaze.scrobbling.shikimori.data.ShikimoriRepository
import javax.inject.Inject
import javax.inject.Provider

class ScrobblerRepositoryMap @Inject constructor(
	private val shikimoriRepository: Provider<ShikimoriRepository>,
	private val aniListRepository: Provider<AniListRepository>,
	private val malRepository: Provider<MALRepository>,
	private val kitsuRepository: Provider<KitsuRepository>,
) {

	operator fun get(scrobblerService: ScrobblerService): ScrobblerRepository = when (scrobblerService) {
		ScrobblerService.SHIKIMORI -> shikimoriRepository
		ScrobblerService.ANILIST -> aniListRepository
		ScrobblerService.MAL -> malRepository
		ScrobblerService.KITSU -> kitsuRepository
	}.get()
}
