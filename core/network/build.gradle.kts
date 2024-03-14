plugins {
    alias(libs.plugins.fanimo.android.library)
    alias(libs.plugins.fanimo.android.library.jacoco)
    alias(libs.plugins.fanimo.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.apollographql.apollo3") version "4.0.0-beta.4"
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
    implementation(libs.apollo.runtime)
//    implementation("com.apollographql.apollo3:apollo-runtime:4.0.0-beta.4")
    testImplementation(projects.core.testing)
}

apollo {

    service("djelenor") {

        packageName.set("com.fanimo.ecommerce.core.network.graphql")
        introspection {
            endpointUrl.set("http://10.0.2.2:8000/graphql/")
            headers.put("api-key", "1234567890abcdef")
            schemaFile.set(file("src/main/graphql/schema.graphqls"))
        }

    }

}