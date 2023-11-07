plugins {
    alias(libs.plugins.fanimo.android.feature)
    alias(libs.plugins.fanimo.android.library.compose)
    alias(libs.plugins.fanimo.android.library.jacoco)
}

android {
    namespace = "com.fanimo.ecommerce.elenor.feature.home"
}

dependencies {
    implementation(libs.androidx.compose.material3.windowSizeClass)
}