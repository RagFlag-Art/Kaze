package org.ragflag.kaze.details.domain

import org.ragflag.kaze.core.util.LocaleStringComparator
import org.ragflag.kaze.details.ui.model.MangaBranch

class BranchComparator : Comparator<MangaBranch> {

	private val delegate = LocaleStringComparator()

	override fun compare(o1: MangaBranch, o2: MangaBranch): Int = delegate.compare(o1.name, o2.name)
}
