package org.ragflag.kaze.details.ui.scrobbling

import org.ragflag.kaze.core.nav.AppRouter
import org.ragflag.kaze.core.ui.BaseListAdapter
import org.ragflag.kaze.list.ui.model.ListModel

class ScrollingInfoAdapter(
	router: AppRouter,
) : BaseListAdapter<ListModel>() {

	init {
		delegatesManager.addDelegate(scrobblingInfoAD(router))
	}
}
