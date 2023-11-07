
plugins {
    alias(libs.plugins.fanimo.android.library)
    alias(libs.plugins.fanimo.android.library.jacoco)
    alias(libs.plugins.fanimo.android.hilt)
}

android {
    defaultConfig {
        testInstrumentationRunner = "com.fanimo.ecommerce.core.testing.eleTestRunner"
    }
    namespace = "com.fanimo.ecommerce.elenor.sync"
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.datastore)
    implementation(projects.core.model)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.tracing.ktx)
    implementation(libs.androidx.work.ktx)
    implementation(libs.hilt.ext.work)
    implementation(libs.kotlinx.coroutines.android)

    prodImplementation(libs.firebase.cloud.messaging)

    kapt(libs.hilt.ext.compiler)

    testImplementation(projects.core.testing)

    androidTestImplementation(projects.core.testing)
    androidTestImplementation(libs.androidx.work.testing)
}
    
