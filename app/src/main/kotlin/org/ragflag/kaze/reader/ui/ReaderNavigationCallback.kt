package org.ragflag.kaze.reader.ui

import org.ragflag.kaze.bookmarks.domain.Bookmark
import org.ragflag.kaze.parsers.model.MangaChapter
import org.ragflag.kaze.reader.ui.pager.ReaderPage

interface ReaderNavigationCallback {

	fun onPageSelected(page: ReaderPage): Boolean

	fun onChapterSelected(chapter: MangaChapter): Boolean

	fun onBookmarkSelected(bookmark: Bookmark): Boolean = onPageSelected(
		ReaderPage(bookmark.toMangaPage(), bookmark.page, bookmark.chapterId),
	)
}
