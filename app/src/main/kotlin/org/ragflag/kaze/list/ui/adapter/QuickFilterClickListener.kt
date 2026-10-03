package org.ragflag.kaze.list.ui.adapter

import org.ragflag.kaze.list.domain.ListFilterOption

interface QuickFilterClickListener {

	fun onFilterOptionClick(option: ListFilterOption)
}
