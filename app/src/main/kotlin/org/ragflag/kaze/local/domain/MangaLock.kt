package org.ragflag.kaze.local.domain

import org.ragflag.kaze.core.util.MultiMutex
import org.ragflag.kaze.parsers.model.Manga
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MangaLock @Inject constructor() : MultiMutex<Manga>()
