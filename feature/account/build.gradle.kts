

plugins {
    alias(libs.plugins.fanimo.android.feature)
    alias(libs.plugins.fanimo.android.library.compose)
    alias(libs.plugins.fanimo.android.library.jacoco)
}

android {
    namespace = "com.fanimo.ecommerce.elenor.feature.account"
}

dependencies {
    //SMSRetrieval API Dependencies for Auto OTP Verification
    implementation(libs.play.services.auth.api.phone)
    implementation(libs.play.services.auth)
}