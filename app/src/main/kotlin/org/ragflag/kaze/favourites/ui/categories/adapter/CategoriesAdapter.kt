package org.ragflag.kaze.favourites.ui.categories.adapter

import org.ragflag.kaze.core.ui.ReorderableListAdapter
import org.ragflag.kaze.favourites.ui.categories.FavouriteCategoriesListListener
import org.ragflag.kaze.list.ui.adapter.ListItemType
import org.ragflag.kaze.list.ui.adapter.ListStateHolderListener
import org.ragflag.kaze.list.ui.adapter.emptyStateListAD
import org.ragflag.kaze.list.ui.adapter.loadingStateAD
import org.ragflag.kaze.list.ui.model.ListModel

class CategoriesAdapter(
	onItemClickListener: FavouriteCategoriesListListener,
	listListener: ListStateHolderListener,
) : ReorderableListAdapter<ListModel>() {

	init {
		addDelegate(ListItemType.CATEGORY_LARGE, categoryAD(onItemClickListener))
		addDelegate(ListItemType.NAV_ITEM, allCategoriesAD(onItemClickListener))
		addDelegate(ListItemType.STATE_EMPTY, emptyStateListAD(listListener))
		addDelegate(ListItemType.STATE_LOADING, loadingStateAD())
	}
}
