@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
}

android {
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs + "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    // Module
    implementation(projects.presentation.viewmodel)
    implementation(projects.domain.model)
    // kotlinx.coroutines
    implementation(libs.kotlinx.coroutines.core)
    // Dagger
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    // AndroidX Compose UI
    implementation(libs.androidx.compose.ui)
    // AndroidX Compose Tooling support
    implementation(libs.androidx.compose.ui.tooling)
    // AndroidX Compose Foundation
    implementation(libs.androidx.compose.fondation)
    // AndroidX Compose Material Design
    implementation(libs.androidx.compose.material3)
    // AndroidX Compose Material Design icons
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.material.icons.extended)
    // AndroidX Compose Integration with activities
    implementation(libs.androidx.activity.compose)
    // AndroidX Compose Integration with ViewModels
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Accompanist Swipe Refresh
    implementation(libs.accompanist.swiperefresh)
    // Accompanist System UI Controller
    implementation(libs.accompanist.systemuicontroller)
    // Coil for Jetpack Compose
    implementation(libs.coil.compose)

    // JUnit
    testImplementation(libs.junit)

    // AndroidX JUnit
    androidTestImplementation(libs.androidx.junit)
    // AndroidX Espresso
    androidTestImplementation(libs.androidx.espresso.core)
    // AndroidX Compose UI Tests
    androidTestImplementation(libs.androidx.compose.ui.test)
}
