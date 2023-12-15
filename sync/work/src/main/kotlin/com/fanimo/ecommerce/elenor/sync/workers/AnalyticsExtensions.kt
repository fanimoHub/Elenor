

package com.fanimo.ecommerce.elenor.sync.workers

import com.fanimo.ecommerce.core.analytics.AnalyticsEvent
import com.fanimo.ecommerce.core.analytics.AnalyticsHelper

fun AnalyticsHelper.logSyncStarted() =
    logEvent(
        AnalyticsEvent(type = "network_sync_started"),
    )

fun AnalyticsHelper.logSyncFinished(syncedSuccessfully: Boolean) {
    val eventType = if (syncedSuccessfully) "network_sync_successful" else "network_sync_failed"
    logEvent(
        AnalyticsEvent(type = eventType),
    )
}
