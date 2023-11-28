plugins {
    alias(libs.plugins.fanimo.android.feature)
    alias(libs.plugins.fanimo.android.library.compose)
    alias(libs.plugins.fanimo.android.library.jacoco)
}

android {
    namespace = "com.fanimo.ecommerce.elenor.feature.settings"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.google.oss.licenses) {
        exclude(group = "androidx.appcompat")
    }
}
