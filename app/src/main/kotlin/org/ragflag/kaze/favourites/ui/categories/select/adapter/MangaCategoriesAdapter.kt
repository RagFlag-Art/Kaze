package org.ragflag.kaze.favourites.ui.categories.select.adapter

import org.ragflag.kaze.core.ui.BaseListAdapter
import org.ragflag.kaze.core.ui.list.OnListItemClickListener
import org.ragflag.kaze.favourites.ui.categories.select.model.MangaCategoryItem
import org.ragflag.kaze.list.ui.adapter.ListItemType
import org.ragflag.kaze.list.ui.adapter.emptyStateListAD
import org.ragflag.kaze.list.ui.adapter.loadingStateAD
import org.ragflag.kaze.list.ui.model.ListModel

class MangaCategoriesAdapter(
	clickListener: OnListItemClickListener<MangaCategoryItem>,
) : BaseListAdapter<ListModel>() {

	init {
		addDelegate(ListItemType.NAV_ITEM, mangaCategoryAD(clickListener))
		addDelegate(ListItemType.STATE_LOADING, loadingStateAD())
		addDelegate(ListItemType.STATE_EMPTY, emptyStateListAD(null))
	}
}
