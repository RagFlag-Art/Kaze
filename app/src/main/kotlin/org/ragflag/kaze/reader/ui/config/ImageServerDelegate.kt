package org.ragflag.kaze.reader.ui.config

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import org.ragflag.kaze.R
import org.ragflag.kaze.core.parser.MangaRepository
import org.ragflag.kaze.core.parser.ParserMangaRepository
import org.ragflag.kaze.parsers.config.ConfigKey
import org.ragflag.kaze.parsers.model.MangaSource
import org.ragflag.kaze.parsers.util.mapToArray
import org.ragflag.kaze.parsers.util.suspendlazy.getOrNull
import org.ragflag.kaze.parsers.util.suspendlazy.suspendLazy
import kotlin.coroutines.resume

class ImageServerDelegate(
	private val mangaRepositoryFactory: MangaRepository.Factory,
	private val mangaSource: MangaSource?,
) {

	private val repositoryLazy = suspendLazy {
		mangaRepositoryFactory.create(checkNotNull(mangaSource)) as ParserMangaRepository
	}

	suspend fun isAvailable() = withContext(Dispatchers.Default) {
		repositoryLazy.getOrNull()?.let { repository ->
			repository.getConfigKeys().any { it is ConfigKey.PreferredImageServer }
		} == true
	}

	suspend fun getValue(): String? = withContext(Dispatchers.Default) {
		repositoryLazy.getOrNull()?.let { repository ->
			val key = repository.getConfigKeys().firstNotNullOfOrNull { it as? ConfigKey.PreferredImageServer }
			if (key != null) {
				key.presetValues[repository.getConfig()[key]]
			} else {
				null
			}
		}
	}

	suspend fun showDialog(context: Context): Boolean {
		val repository = withContext(Dispatchers.Default) {
			repositoryLazy.getOrNull()
		} ?: return false
		val key = repository.getConfigKeys().firstNotNullOfOrNull {
			it as? ConfigKey.PreferredImageServer
		} ?: return false
		val entries = key.presetValues.values.mapToArray {
			it ?: context.getString(R.string.automatic)
		}
		val entryValues = key.presetValues.keys.toTypedArray()
		val config = repository.getConfig()
		val initialValue = config[key]
		var currentValue = initialValue
		val changed = suspendCancellableCoroutine { cont ->
			val dialog = MaterialAlertDialogBuilder(context)
				.setTitle(R.string.image_server)
				.setCancelable(true)
				.setSingleChoiceItems(entries, entryValues.indexOf(initialValue)) { _, i ->
					currentValue = entryValues[i]
				}.setNegativeButton(android.R.string.cancel) { dialog, _ ->
					dialog.cancel()
				}.setPositiveButton(android.R.string.ok) { _, _ ->
					if (currentValue != initialValue) {
						config[key] = currentValue
						cont.resume(true)
					} else {
						cont.resume(false)
					}
				}.setOnCancelListener {
					cont.resume(false)
				}.create()
			dialog.show()
			cont.invokeOnCancellation {
				dialog.cancel()
			}
		}
		if (changed) {
			repository.invalidateCache()
		}
		return changed
	}
}
