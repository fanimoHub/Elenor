
package com.fanimo.ecommerce.core.notifications

import com.fanimo.ecommerce.core.model.data.NewsResource

/**
 * Interface for creating notifications in the app
 */
interface Notifier {
    fun postNewsNotifications(newsResources: List<NewsResource>)
}
