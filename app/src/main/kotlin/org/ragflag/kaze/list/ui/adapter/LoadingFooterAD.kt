package org.ragflag.kaze.list.ui.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import org.ragflag.kaze.R
import org.ragflag.kaze.list.ui.model.ListModel
import org.ragflag.kaze.list.ui.model.LoadingFooter

fun loadingFooterAD() = adapterDelegate<LoadingFooter, ListModel>(R.layout.item_loading_footer) {
}