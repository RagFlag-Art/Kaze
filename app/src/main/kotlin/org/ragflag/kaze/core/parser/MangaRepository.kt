package org.ragflag.kaze.core.parser

import android.content.Context
import androidx.annotation.AnyThread
import androidx.collection.ArrayMap
import dagger.hilt.android.qualifiers.ApplicationContext
import org.ragflag.kaze.core.cache.MemoryContentCache
import org.ragflag.kaze.core.model.LocalMangaSource
import org.ragflag.kaze.core.model.MangaSourceInfo
import org.ragflag.kaze.core.model.TestMangaSource
import org.ragflag.kaze.core.model.UnknownMangaSource
import org.ragflag.kaze.core.parser.external.ExternalMangaRepository
import org.ragflag.kaze.core.parser.external.ExternalMangaSource
import org.ragflag.kaze.local.data.LocalMangaRepository
import org.ragflag.kaze.parsers.MangaLoaderContext
import org.ragflag.kaze.parsers.model.Manga
import org.ragflag.kaze.parsers.model.MangaChapter
import org.ragflag.kaze.parsers.model.MangaListFilter
import org.ragflag.kaze.parsers.model.MangaListFilterCapabilities
import org.ragflag.kaze.parsers.model.MangaListFilterOptions
import org.ragflag.kaze.parsers.model.MangaPage
import org.ragflag.kaze.parsers.model.MangaParserSource
import org.ragflag.kaze.parsers.model.MangaSource
import org.ragflag.kaze.parsers.model.SortOrder
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

interface MangaRepository {

	val source: MangaSource

	val sortOrders: Set<SortOrder>

	var defaultSortOrder: SortOrder

	val filterCapabilities: MangaListFilterCapabilities

	suspend fun getList(offset: Int, order: SortOrder?, filter: MangaListFilter?): List<Manga>

	suspend fun getDetails(manga: Manga): Manga

	suspend fun getPages(chapter: MangaChapter): List<MangaPage>

	suspend fun getPageUrl(page: MangaPage): String

	suspend fun getFilterOptions(): MangaListFilterOptions

	suspend fun getRelated(seed: Manga): List<Manga>

	suspend fun find(manga: Manga): Manga? {
		val list = getList(0, SortOrder.RELEVANCE, MangaListFilter(query = manga.title))
		return list.find { x -> x.id == manga.id }
	}

	@Singleton
	class Factory @Inject constructor(
		@ApplicationContext private val context: Context,
		private val localMangaRepository: LocalMangaRepository,
		private val loaderContext: MangaLoaderContext,
		private val contentCache: MemoryContentCache,
		private val mirrorSwitcher: MirrorSwitcher,
	) {

		private val cache = ArrayMap<MangaSource, WeakReference<MangaRepository>>()

		@AnyThread
		fun create(source: MangaSource): MangaRepository {
			when (source) {
				is MangaSourceInfo -> return create(source.mangaSource)
				LocalMangaSource -> return localMangaRepository
				UnknownMangaSource -> return EmptyMangaRepository(source)
			}
			cache[source]?.get()?.let { return it }
			return synchronized(cache) {
				cache[source]?.get()?.let { return it }
				val repository = createRepository(source)
				if (repository != null) {
					cache[source] = WeakReference(repository)
					repository
				} else {
					EmptyMangaRepository(source)
				}
			}
		}

		private fun createRepository(source: MangaSource): MangaRepository? = when (source) {
			is MangaParserSource -> ParserMangaRepository(
				parser = loaderContext.newParserInstance(source),
				cache = contentCache,
				mirrorSwitcher = mirrorSwitcher,
			)

			TestMangaSource -> TestMangaRepository(
				loaderContext = loaderContext,
				cache = contentCache,
			)

			is ExternalMangaSource -> if (source.isAvailable(context)) {
				ExternalMangaRepository(
					contentResolver = context.contentResolver,
					source = source,
					cache = contentCache,
				)
			} else {
				EmptyMangaRepository(source)
			}

			else -> null
		}
	}
}
