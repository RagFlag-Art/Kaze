package org.ragflag.kaze.explore.ui.adapter

import android.view.View
import org.ragflag.kaze.list.ui.adapter.ListHeaderClickListener
import org.ragflag.kaze.list.ui.adapter.ListStateHolderListener

interface ExploreListEventListener : ListStateHolderListener, View.OnClickListener, ListHeaderClickListener
