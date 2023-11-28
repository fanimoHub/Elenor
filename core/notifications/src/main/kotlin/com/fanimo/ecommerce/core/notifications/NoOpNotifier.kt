
package com.fanimo.ecommerce.core.notifications

import com.fanimo.ecommerce.core.model.data.NewsResource
import com.fanimo.ecommerce.core.notifications.Notifier
import javax.inject.Inject

/**
 * Implementation of [Notifier] which does nothing. Useful for tests and previews.
 */
class NoOpNotifier @Inject constructor() : Notifier {
    override fun postNewsNotifications(newsResources: List<NewsResource>) = Unit
}
