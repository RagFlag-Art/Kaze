package org.ragflag.kaze.core.exceptions

import org.ragflag.kaze.details.ui.pager.EmptyMangaReason
import org.ragflag.kaze.parsers.model.Manga

class EmptyMangaException(
    val reason: EmptyMangaReason?,
    val manga: Manga,
    cause: Throwable?
) : IllegalStateException(cause)
