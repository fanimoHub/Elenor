
package com.fanimo.ecommerce.core.datastore

import androidx.datastore.core.DataMigration


object IntToStringIdsMigration : DataMigration<UserPreferences> {

    override suspend fun cleanUp() = Unit

    override suspend fun migrate(currentData: UserPreferences): UserPreferences =
        currentData.copy {
            // Migrate topic ids
            deprecatedFollowedTopicIds.clear()
            deprecatedFollowedTopicIds.addAll(
                currentData.deprecatedIntFollowedTopicIdsList.map(Int::toString),
            )
            deprecatedIntFollowedTopicIds.clear()

            // Migrate author ids
            deprecatedFollowedAuthorIds.clear()
            deprecatedFollowedAuthorIds.addAll(
                currentData.deprecatedIntFollowedAuthorIdsList.map(Int::toString),
            )
            deprecatedIntFollowedAuthorIds.clear()

            // Mark migration as complete
            hasDoneIntToStringIdMigration = true
        }

    override suspend fun shouldMigrate(currentData: UserPreferences): Boolean =
        !currentData.hasDoneIntToStringIdMigration
}
