package org.ragflag.kaze.favourites.ui

import android.os.Bundle
import org.ragflag.kaze.core.nav.AppRouter
import org.ragflag.kaze.core.ui.FragmentContainerActivity
import org.ragflag.kaze.favourites.ui.list.FavouritesListFragment

class FavouritesActivity : FragmentContainerActivity(FavouritesListFragment::class.java) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val categoryTitle = intent.getStringExtra(AppRouter.KEY_TITLE)
		if (categoryTitle != null) {
			title = categoryTitle
		}
	}
}
