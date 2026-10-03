package org.ragflag.kaze.core.parser

import org.ragflag.kaze.core.cache.MemoryContentCache
import org.ragflag.kaze.core.model.TestMangaSource
import org.ragflag.kaze.parsers.MangaLoaderContext

@Suppress("unused")
class TestMangaRepository(
    private val loaderContext: MangaLoaderContext,
    cache: MemoryContentCache
) : EmptyMangaRepository(TestMangaSource)
