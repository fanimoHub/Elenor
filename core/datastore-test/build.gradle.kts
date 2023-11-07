
plugins {
    alias(libs.plugins.fanimo.android.library)
    alias(libs.plugins.fanimo.android.hilt)
}

android {
    namespace = "com.fanimo.ecommerce.core.datastore_test"
}

dependencies {
    api(projects.core.datastore)
    api(libs.androidx.dataStore.core)

    implementation(libs.protobuf.kotlin.lite)
    implementation(projects.core.common)
    implementation(projects.core.testing)
}
