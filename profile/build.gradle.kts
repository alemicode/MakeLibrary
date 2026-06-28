plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.farsitel.panjere.profile"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Note: These dependencies are currently missing in the project
    // implementation(project(":library:core"))
    // implementation(project(":library:designsystem"))
    // implementation(project(":library:datastore"))
    // implementation(project(":common:account"))
    // implementation(project(":common:bookmark"))
    // implementation(project(":common:page"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.runtime.ktx)
}
