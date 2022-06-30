@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.kazakago.blueprint.presentation.controller"
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
    libraryVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin") // https://github.com/google/ksp/issues/37
            }
        }
    }
}

dependencies {
    // Modules
    implementation(projects.presentation.ui)
    implementation(projects.presentation.uistate)
    implementation(projects.domain.usecase)
    implementation(projects.domain.model)
    // Kotlinx Coroutines
    implementation(libs.kotlinx.coroutines.core)
    // Dagger
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    // AndroidX Compose Runtime
    implementation(libs.androidx.compose.runtime)
    // AndroidX Activity Compose
    implementation(libs.androidx.activity.compose)
    // AndroidX Navigation Compose
    implementation(libs.androidx.navigation.compose)
    // AndroidX Hilt Navigation Compose
    implementation(libs.androidx.hilt.navigation.compose)
    // AndroidX Lifecycle ViewModel
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.common)
    // AndroidX Lifecycle Runtime
    implementation(libs.androidx.lifecycle.runtime)
    // Compose Destinations
    implementation(libs.compose.distinations.core)
    ksp(libs.compose.distinations.ksp)
    // StoreFlowable.kt
    implementation(libs.storeflowable.core)

    // JUnit
    testImplementation(libs.junit)

    // AndroidX JUnit
    androidTestImplementation(libs.androidx.junit)
    // AndroidX Espresso
    androidTestImplementation(libs.androidx.espresso.core)
}
