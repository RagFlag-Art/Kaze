package org.ragflag.kaze.settings.nav.model

import androidx.annotation.StringRes
import org.ragflag.kaze.core.prefs.NavItem
import org.ragflag.kaze.list.ui.model.ListModel

data class NavItemConfigModel(
	val item: NavItem,
	@StringRes val disabledHintResId: Int,
) : ListModel {

	override fun areItemsTheSame(other: ListModel): Boolean {
		return other is NavItemConfigModel && other.item == item
	}
}
