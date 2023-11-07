

    plugins {
        alias(libs.plugins.fanimo.android.library)
        alias(libs.plugins.fanimo.android.hilt)
    }

    android {
        namespace = "com.fanimo.ecommerce.elenor.core.sync.test"
    }

    dependencies {
        api(projects.sync.work)
        implementation(projects.core.data)
        implementation(projects.core.testing)
    }
