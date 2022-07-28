@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.kazakago.blueprint.presentation.ui"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    flavorDimensions += "app"
    productFlavors {
        create("production") {
            dimension = "app"
        }
        create("develop") {
            dimension = "app"
        }
    }
    compileOptions {
        sourceCompatibility(libs.versions.java.get())
        targetCompatibility(libs.versions.java.get())
    }
    kotlinOptions {
        jvmTarget = libs.versions.java.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    // Modules
    implementation(projects.presentation.uistate)
    implementation(projects.domain.model)
    // Kotlinx Coroutines
    implementation(libs.kotlinx.coroutines.core)
    // AndroidX Compose UI
    implementation(libs.androidx.compose.ui)
    // AndroidX Compose Tooling support
    implementation(libs.androidx.compose.ui.tooling)
    // AndroidX Compose Foundation
    implementation(libs.androidx.compose.fondation)
    // AndroidX Compose Material3
    implementation(libs.androidx.compose.material3)
    // AndroidX Compose Material Icons
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    // Accompanist Swipe Refresh
    implementation(libs.accompanist.swiperefresh)
    // Accompanist System UI Controller
    implementation(libs.accompanist.systemuicontroller)
    // Coil
    implementation(libs.coil.compose)

    // Need for Compose preview, bug of AS.
    // Updating AS to Dolphin will fix it.
    // https://issuetracker.google.com/issues/227767363
    debugImplementation("androidx.activity:activity-ktx:1.5.1")
    debugImplementation("androidx.customview:customview-poolingcontainer:1.0.0-rc01")

    // JUnit
    testImplementation(libs.junit)

    // AndroidX JUnit
    androidTestImplementation(libs.androidx.junit)
    // AndroidX Espresso
    androidTestImplementation(libs.androidx.espresso.core)
    // AndroidX Compose UI Tests
    androidTestImplementation(libs.androidx.compose.ui.test)
}
