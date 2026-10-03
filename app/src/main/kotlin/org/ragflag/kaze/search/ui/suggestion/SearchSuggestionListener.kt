package org.ragflag.kaze.search.ui.suggestion

import android.text.TextWatcher
import android.widget.TextView
import org.ragflag.kaze.parsers.model.Manga
import org.ragflag.kaze.parsers.model.MangaSource
import org.ragflag.kaze.parsers.model.MangaTag
import org.ragflag.kaze.search.domain.SearchKind

interface SearchSuggestionListener : TextWatcher, TextView.OnEditorActionListener {

	fun onMangaClick(manga: Manga)

	fun onQueryClick(query: String, kind: SearchKind, submit: Boolean)

	fun onSourceToggle(source: MangaSource, isEnabled: Boolean)

	fun onSourceClick(source: MangaSource)

	fun onTagClick(tag: MangaTag)
}
