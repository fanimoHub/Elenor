

    plugins {
        alias(libs.plugins.fanimo.android.library)
        alias(libs.plugins.fanimo.android.library.compose)
        alias(libs.plugins.fanimo.android.hilt)
    }

    android {
        namespace = "com.fanimo.ecommerce.core.analytics"
    }

    dependencies {
        implementation(platform(libs.firebase.bom))
        implementation(libs.androidx.compose.runtime)
        implementation(libs.androidx.core.ktx)
        implementation(libs.firebase.analytics)
        implementation(libs.kotlinx.coroutines.android)
    }
