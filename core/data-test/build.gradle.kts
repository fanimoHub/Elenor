

    plugins {
        alias(libs.plugins.fanimo.android.library)
        alias(libs.plugins.fanimo.android.hilt)
    }

    android {
        namespace = "com.fanimo.ecommerce.core.data_test"
    }

    dependencies {
        api(projects.core.data)
        implementation(projects.core.testing)
        implementation(projects.core.common)
    }

