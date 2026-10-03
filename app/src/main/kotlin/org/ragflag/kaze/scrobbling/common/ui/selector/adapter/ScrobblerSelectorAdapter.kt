package org.ragflag.kaze.scrobbling.common.ui.selector.adapter

import org.ragflag.kaze.core.ui.BaseListAdapter
import org.ragflag.kaze.core.ui.list.OnListItemClickListener
import org.ragflag.kaze.list.ui.adapter.ListItemType
import org.ragflag.kaze.list.ui.adapter.ListStateHolderListener
import org.ragflag.kaze.list.ui.adapter.loadingFooterAD
import org.ragflag.kaze.list.ui.adapter.loadingStateAD
import org.ragflag.kaze.list.ui.model.ListModel
import org.ragflag.kaze.scrobbling.common.domain.model.ScrobblerManga

class ScrobblerSelectorAdapter(
	clickListener: OnListItemClickListener<ScrobblerManga>,
	stateHolderListener: ListStateHolderListener,
) : BaseListAdapter<ListModel>() {

	init {
		addDelegate(ListItemType.STATE_LOADING, loadingStateAD())
		addDelegate(ListItemType.MANGA_SCROBBLING, scrobblingMangaAD(clickListener))
		addDelegate(ListItemType.FOOTER_LOADING, loadingFooterAD())
		addDelegate(ListItemType.HINT_EMPTY, scrobblerHintAD(stateHolderListener))
	}
}
