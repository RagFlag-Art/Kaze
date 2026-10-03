package org.ragflag.kaze.download.ui.list

import androidx.lifecycle.LifecycleOwner
import org.ragflag.kaze.core.ui.BaseListAdapter
import org.ragflag.kaze.list.ui.adapter.ListItemType
import org.ragflag.kaze.list.ui.adapter.emptyStateListAD
import org.ragflag.kaze.list.ui.adapter.listHeaderAD
import org.ragflag.kaze.list.ui.adapter.loadingStateAD
import org.ragflag.kaze.list.ui.model.ListModel

class DownloadsAdapter(
	lifecycleOwner: LifecycleOwner,
	listener: DownloadItemListener,
) : BaseListAdapter<ListModel>() {

	init {
		addDelegate(ListItemType.DOWNLOAD, downloadItemAD(lifecycleOwner, listener))
		addDelegate(ListItemType.STATE_LOADING, loadingStateAD())
		addDelegate(ListItemType.STATE_EMPTY, emptyStateListAD(null))
		addDelegate(ListItemType.HEADER, listHeaderAD(null))
	}
}
