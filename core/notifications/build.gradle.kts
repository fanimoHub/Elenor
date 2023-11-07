plugins {
    alias(libs.plugins.fanimo.android.library)
    alias(libs.plugins.fanimo.android.library.compose)
    alias(libs.plugins.fanimo.android.hilt)
}

android {
    namespace = "com.fanimo.ecommerce.core.notifications"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.cloud.messaging)
}
