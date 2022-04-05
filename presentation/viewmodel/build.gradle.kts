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
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}

dependencies {
    // Module
    implementation(projects.domain.usecase)
    implementation(projects.domain.model)
    // kotlinx.coroutines
    implementation(libs.kotlinx.coroutines.core)
    // Dagger
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    // AndroidX Compose Runtime
    implementation(libs.androidx.compose.runtime)
    // AndroidX Lifecycle
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.common)
    // StoreFlowable.kt
    implementation(libs.storeflowable.core)

    // JUnit
    testImplementation(libs.junit)

    // AndroidX JUnit
    androidTestImplementation(libs.androidx.junit)
    // AndroidX Espresso
    androidTestImplementation(libs.androidx.espresso.core)
}
