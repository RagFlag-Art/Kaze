package org.ragflag.kaze.history.ui

import android.content.Context
import org.ragflag.kaze.core.ui.list.fastscroll.FastScroller
import org.ragflag.kaze.list.ui.adapter.MangaListAdapter
import org.ragflag.kaze.list.ui.adapter.MangaListListener
import org.ragflag.kaze.list.ui.size.ItemSizeResolver

class HistoryListAdapter(
	listener: MangaListListener,
	sizeResolver: ItemSizeResolver,
) : MangaListAdapter(listener, sizeResolver), FastScroller.SectionIndexer {

	override fun getSectionText(context: Context, position: Int): CharSequence? {
		return findHeader(position)?.getText(context)
	}
}
