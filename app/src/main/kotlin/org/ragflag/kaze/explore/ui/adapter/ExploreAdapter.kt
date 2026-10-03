package org.ragflag.kaze.explore.ui.adapter

import org.ragflag.kaze.core.ui.BaseListAdapter
import org.ragflag.kaze.core.ui.list.OnListItemClickListener
import org.ragflag.kaze.explore.ui.model.MangaSourceItem
import org.ragflag.kaze.list.ui.adapter.ListItemType
import org.ragflag.kaze.list.ui.adapter.emptyHintAD
import org.ragflag.kaze.list.ui.adapter.listHeaderAD
import org.ragflag.kaze.list.ui.adapter.loadingStateAD
import org.ragflag.kaze.list.ui.model.ListModel
import org.ragflag.kaze.parsers.model.Manga

class ExploreAdapter(
	listener: ExploreListEventListener,
	clickListener: OnListItemClickListener<MangaSourceItem>,
	mangaClickListener: OnListItemClickListener<Manga>,
) : BaseListAdapter<ListModel>() {

	init {
		addDelegate(ListItemType.EXPLORE_BUTTONS, exploreButtonsAD(listener))
		addDelegate(
			ListItemType.EXPLORE_SUGGESTION,
			exploreRecommendationItemAD(mangaClickListener),
		)
		addDelegate(ListItemType.HEADER, listHeaderAD(listener))
		addDelegate(ListItemType.EXPLORE_SOURCE_LIST, exploreSourceListItemAD(clickListener))
		addDelegate(ListItemType.EXPLORE_SOURCE_GRID, exploreSourceGridItemAD(clickListener))
		addDelegate(ListItemType.HINT_EMPTY, emptyHintAD(listener))
		addDelegate(ListItemType.STATE_LOADING, loadingStateAD())
	}
}
