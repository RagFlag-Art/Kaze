package org.ragflag.kaze.tracker.ui.feed.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.ragflag.kaze.R
import org.ragflag.kaze.core.ui.BaseListAdapter
import org.ragflag.kaze.core.ui.list.OnListItemClickListener
import org.ragflag.kaze.databinding.ItemListGroupBinding
import org.ragflag.kaze.list.ui.adapter.ListHeaderClickListener
import org.ragflag.kaze.list.ui.adapter.ListItemType
import org.ragflag.kaze.list.ui.adapter.mangaGridItemAD
import org.ragflag.kaze.list.ui.model.ListHeader
import org.ragflag.kaze.list.ui.model.ListModel
import org.ragflag.kaze.list.ui.model.MangaListModel
import org.ragflag.kaze.list.ui.size.ItemSizeResolver
import org.ragflag.kaze.tracker.ui.feed.model.UpdatedMangaHeader

fun updatedMangaAD(
	sizeResolver: ItemSizeResolver,
	listener: OnListItemClickListener<MangaListModel>,
	headerClickListener: ListHeaderClickListener,
) = adapterDelegateViewBinding<UpdatedMangaHeader, ListModel, ItemListGroupBinding>(
	{ layoutInflater, parent -> ItemListGroupBinding.inflate(layoutInflater, parent, false) },
) {

	val adapter = BaseListAdapter<ListModel>()
		.addDelegate(ListItemType.MANGA_GRID, mangaGridItemAD(sizeResolver, listener))
	binding.recyclerView.adapter = adapter
	binding.buttonMore.setOnClickListener { v ->
		headerClickListener.onListHeaderClick(ListHeader(0, payload = item), v)
	}
	binding.textViewTitle.setText(R.string.updates)
	binding.buttonMore.setText(R.string.more)

	bind {
		adapter.items = item.list
	}
}
