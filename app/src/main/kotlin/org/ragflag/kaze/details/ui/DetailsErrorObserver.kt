package org.ragflag.kaze.details.ui

import com.google.android.material.snackbar.Snackbar
import org.ragflag.kaze.R
import org.ragflag.kaze.core.exceptions.UnsupportedSourceException
import org.ragflag.kaze.core.exceptions.resolve.ErrorObserver
import org.ragflag.kaze.core.exceptions.resolve.ExceptionResolver
import org.ragflag.kaze.core.util.ext.getDisplayMessage
import org.ragflag.kaze.core.util.ext.isNetworkError
import org.ragflag.kaze.core.util.ext.isSerializable
import org.ragflag.kaze.parsers.exception.NotFoundException
import org.ragflag.kaze.parsers.exception.ParseException

class DetailsErrorObserver(
	override val activity: DetailsActivity,
	private val viewModel: DetailsViewModel,
	resolver: ExceptionResolver?,
) : ErrorObserver(
	activity.viewBinding.scrollView, null, resolver,
	{ isResolved ->
		if (isResolved) {
			viewModel.reload()
		}
	},
) {

	override suspend fun emit(value: Throwable) {
		val snackbar = Snackbar.make(host, value.getDisplayMessage(host.context.resources), Snackbar.LENGTH_SHORT)
		snackbar.setAnchorView(activity.viewBinding.containerBottomSheet)
		if (value is NotFoundException || value is UnsupportedSourceException) {
			snackbar.duration = Snackbar.LENGTH_INDEFINITE
		}
		when {
			canResolve(value) -> {
				snackbar.setAction(ExceptionResolver.getResolveStringId(value)) {
					resolve(value)
				}
			}

			value is ParseException -> {
				val router = router()
				if (router != null && value.isSerializable()) {
					snackbar.setAction(R.string.details) {
						router.showErrorDialog(value)
					}
				}
			}

			value.isNetworkError() -> {
				snackbar.setAction(R.string.try_again) {
					viewModel.reload()
				}
			}
		}
		snackbar.show()
	}
}
