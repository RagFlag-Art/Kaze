package org.ragflag.kaze.list.ui.model

import org.ragflag.kaze.core.ui.model.MangaOverride
import org.ragflag.kaze.parsers.model.Manga

data class MangaCompactListModel(
	override val manga: Manga,
	override val override: MangaOverride?,
	val subtitle: String,
	override val counter: Int,
) : MangaListModel()
