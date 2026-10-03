package org.ragflag.kaze.details.domain

import org.ragflag.kaze.core.parser.MangaRepository
import org.ragflag.kaze.core.util.ext.printStackTraceDebug
import org.ragflag.kaze.parsers.model.Manga
import org.ragflag.kaze.parsers.util.runCatchingCancellable
import javax.inject.Inject

class RelatedMangaUseCase @Inject constructor(
	private val mangaRepositoryFactory: MangaRepository.Factory,
) {

	suspend operator fun invoke(seed: Manga) = runCatchingCancellable {
		mangaRepositoryFactory.create(seed.source).getRelated(seed)
	}.onFailure {
		it.printStackTraceDebug()
	}.getOrNull()
}
