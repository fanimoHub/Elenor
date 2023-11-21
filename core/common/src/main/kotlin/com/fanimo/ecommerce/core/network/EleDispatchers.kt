
package com.fanimo.ecommerce.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val eleDispatcher: EleDispatchers)

enum class EleDispatchers {
    Default,
    IO,
}
