

    plugins {
        alias(libs.plugins.fanimo.android.library)
        alias(libs.plugins.fanimo.android.library.jacoco)
        alias(libs.plugins.fanimo.android.hilt)
        alias(libs.plugins.fanimo.android.room)
    }

    android {
        defaultConfig {
            testInstrumentationRunner =
                "com.google.samples.apps.fanimo.core.testing.NiaTestRunner"
        }
        namespace = "com.fanimo.ecommerce.core.database"
    }

    dependencies {
        implementation(projects.core.model)
        implementation(libs.kotlinx.coroutines.android)
        implementation(libs.kotlinx.datetime)
        androidTestImplementation(projects.core.testing)
    }