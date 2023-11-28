plugins {
    alias(libs.plugins.fanimo.android.feature)
    alias(libs.plugins.fanimo.android.library.compose)
    alias(libs.plugins.fanimo.android.library.jacoco)
}

android {
    namespace = "com.fanimo.ecommerce.elenor.feature.search"
}

dependencies {
    implementation(projects.feature.home)
    implementation(projects.feature.product)
    implementation(projects.feature.favorite)
    implementation(projects.feature.category)
    implementation(libs.kotlinx.datetime)
}


