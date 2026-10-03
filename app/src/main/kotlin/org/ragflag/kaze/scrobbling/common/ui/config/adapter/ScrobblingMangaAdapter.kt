package org.ragflag.kaze.scrobbling.common.ui.config.adapter

import org.ragflag.kaze.core.ui.BaseListAdapter
import org.ragflag.kaze.core.ui.list.OnListItemClickListener
import org.ragflag.kaze.list.ui.adapter.ListItemType
import org.ragflag.kaze.list.ui.adapter.emptyStateListAD
import org.ragflag.kaze.list.ui.model.ListModel
import org.ragflag.kaze.scrobbling.common.domain.model.ScrobblingInfo

class ScrobblingMangaAdapter(
	clickListener: OnListItemClickListener<ScrobblingInfo>,
) : BaseListAdapter<ListModel>() {

	init {
		addDelegate(ListItemType.HEADER, scrobblingHeaderAD())
		addDelegate(ListItemType.STATE_EMPTY, emptyStateListAD(null))
		addDelegate(ListItemType.MANGA_SCROBBLING, scrobblingMangaAD(clickListener))
	}
}
