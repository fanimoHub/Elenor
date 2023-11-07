

plugins {
        alias(libs.plugins.fanimo.android.library)
        alias(libs.plugins.fanimo.android.library.jacoco)
        alias(libs.plugins.fanimo.android.hilt)
        alias(libs.plugins.kotlin.serialization)
        id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    }

    android {
        buildFeatures {
            buildConfig = true
        }
        namespace = "com.fanimo.ecommerce.core.network"
        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
    }

    secrets {
        defaultPropertiesFileName = "secrets.defaults.properties"
    }

    dependencies {
        implementation(projects.core.common)
        implementation(projects.core.model)
        implementation(libs.coil.kt)
        implementation(libs.coil.kt.svg)
        implementation(libs.kotlinx.coroutines.android)
        implementation(libs.kotlinx.datetime)
        implementation(libs.kotlinx.serialization.json)
        implementation(libs.okhttp.logging)
        implementation(libs.retrofit.core)
        implementation(libs.retrofit.kotlin.serialization)

        testImplementation(projects.core.testing)
    }