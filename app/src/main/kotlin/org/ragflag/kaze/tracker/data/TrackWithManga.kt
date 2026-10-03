package org.ragflag.kaze.tracker.data

import androidx.room.Embedded
import androidx.room.Relation
import org.ragflag.kaze.core.db.entity.MangaEntity

class TrackWithManga(
	@Embedded val track: TrackEntity,
	@Relation(
		parentColumn = "manga_id",
		entityColumn = "manga_id",
	)
	val manga: MangaEntity,
)
