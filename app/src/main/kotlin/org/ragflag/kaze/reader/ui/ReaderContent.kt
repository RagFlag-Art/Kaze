package org.ragflag.kaze.reader.ui

import org.ragflag.kaze.reader.ui.pager.ReaderPage

data class ReaderContent(
	val pages: List<ReaderPage>,
	val state: ReaderState?
)