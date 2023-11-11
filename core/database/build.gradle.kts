

    plugins {
        alias(libs.plugins.fanimo.android.library)
        alias(libs.plugins.fanimo.android.library.jacoco)
        alias(libs.plugins.fanimo.android.hilt)
        alias(libs.plugins.fanimo.android.room)
    }

    android {
        defaultConfig {
            testInstrumentationRunner =
                "com.fanimo.core.testing.EleTestRunner"
        }
        namespace = "com.fanimo.ecommerce.core.database"
    }

    dependencies {
        implementation(projects.core.model)
        implementation(libs.kotlinx.coroutines.android)
        implementation(libs.kotlinx.datetime)
        androidTestImplementation(projects.core.testing)
    }