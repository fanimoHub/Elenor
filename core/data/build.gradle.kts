

plugins {
    alias(libs.plugins.fanimo.android.library)
    alias(libs.plugins.fanimo.android.library.jacoco)
    alias(libs.plugins.fanimo.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.fanimo.ecommerce.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.common)
    implementation(projects.core.datastore)
    implementation(projects.core.network)
    implementation(projects.core.model)
    implementation(projects.core.notifications)
    implementation(projects.core.database)
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(projects.core.datastoreTest)
    testImplementation(projects.core.testing)

}
