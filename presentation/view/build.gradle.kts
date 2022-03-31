plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31
    defaultConfig {
        minSdk = 28
        targetSdk = 31
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
        create("staging") {
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
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {
    // Module
    implementation(project(":presentation:viewmodel"))
    implementation(project(":domain:model"))
    // kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    // Dagger
    implementation("com.google.dagger:hilt-android:2.41")
    kapt("com.google.dagger:hilt-compiler:2.41")
    // AndroidX Compose UI
    implementation("androidx.compose.ui:ui:1.1.1")
    // AndroidX Compose Tooling support
    implementation("androidx.compose.ui:ui-tooling:1.1.1")
    // AndroidX Compose Foundation
    implementation("androidx.compose.foundation:foundation:1.1.1")
    // AndroidX Compose Material Design
    implementation("androidx.compose.material3:material3:1.0.0-alpha08")
    // AndroidX Compose Material Design icons
    implementation("androidx.compose.material:material-icons-core:1.1.1")
    implementation("androidx.compose.material:material-icons-extended:1.1.1")
    // AndroidX Compose Integration with activities
    implementation("androidx.activity:activity-compose:1.4.0")
    // AndroidX Compose Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    // Accompanist Swipe Refresh
    implementation("com.google.accompanist:accompanist-swiperefresh:0.23.1")
    // Accompanist System UI Controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.23.1")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // AndroidX JUnit
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    // AndroidX Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    // AndroidX Compose UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")}
