package org.ragflag.kaze.list.ui.adapter

import android.view.View
import org.ragflag.kaze.list.ui.model.ListHeader

interface ListHeaderClickListener {

	fun onListHeaderClick(item: ListHeader, view: View)
}
