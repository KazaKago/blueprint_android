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
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlinx.coroutines.FlowPreview"
    }
}

dependencies {
    // Module
    implementation(projects.domain.model)
    implementation(projects.domain.repository)
    implementation(projects.data.api)
    implementation(projects.data.cache)
    implementation(projects.data.resource)
    implementation(projects.data.mapper)
    // kotlinx.coroutines
    implementation(libs.kotlinx.coroutines.core)
    // kotlinx-datetime
    implementation(libs.kotlinx.datetime)
    // Dagger
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    // StoreFlowable.kt
    implementation(libs.storeflowable)

    // JUnit
    testImplementation(libs.junit)

    // AndroidX JUnit
    androidTestImplementation(libs.androidx.junit)
    // AndroidX Espresso
    androidTestImplementation(libs.androidx.espresso.core)
}
