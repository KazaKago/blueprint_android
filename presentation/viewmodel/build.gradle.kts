plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30
    defaultConfig {
        minSdk = 28
        targetSdk = 30
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Module
    implementation(project(":domain:usecase"))
    implementation(project(":domain:model"))
    // kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    // Dagger
    implementation("com.google.dagger:hilt-android:2.39.1")
    kapt("com.google.dagger:hilt-compiler:2.39.1")
    // AndroidX Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.3.1")
    // StoreFlowable.kt
    implementation("com.kazakago.storeflowable:storeflowable-core:4.0.0")

    // desugar_jdk_libs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // AndroidX JUnit
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    // AndroidX Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
