
package com.fanimo.convention

/**
 * This is shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class EleBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
    BENCHMARK(".benchmark")
}
