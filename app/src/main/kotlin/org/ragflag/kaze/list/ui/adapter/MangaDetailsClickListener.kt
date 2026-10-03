package org.ragflag.kaze.list.ui.adapter

import android.view.View
import org.ragflag.kaze.core.ui.list.OnListItemClickListener
import org.ragflag.kaze.list.ui.model.MangaListModel
import org.ragflag.kaze.parsers.model.Manga
import org.ragflag.kaze.parsers.model.MangaTag

interface MangaDetailsClickListener : OnListItemClickListener<MangaListModel> {

	fun onReadClick(manga: Manga, view: View)

	fun onTagClick(manga: Manga, tag: MangaTag, view: View)
}
