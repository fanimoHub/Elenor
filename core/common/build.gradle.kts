

plugins {
    alias(libs.plugins.fanimo.android.library)
    alias(libs.plugins.fanimo.android.library.jacoco)
    alias(libs.plugins.fanimo.android.hilt)
}

android {
    namespace = "com.fanimo.ecommerce.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(project(":core:testing"))
}