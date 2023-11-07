
    

    plugins {
        alias(libs.plugins.fanimo.android.library)
        alias(libs.plugins.fanimo.android.library.jacoco)
        kotlin("kapt")
    }

    android {
        namespace = "com.fanimo.ecommerce.core.domain"
    }

    dependencies {
        implementation(projects.core.data)
        implementation(projects.core.model)
        implementation(libs.hilt.android)
        implementation(libs.kotlinx.coroutines.android)
        implementation(libs.kotlinx.datetime)

        kapt(libs.hilt.compiler)

        testImplementation(projects.core.testing)
    }