plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.kazakago.blueprint"
        minSdk = 28
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = " debug"
        }
    }
    flavorDimensions += "app"
    productFlavors {
        create("production") {
            dimension = "app"
        }
        create("staging") {
            dimension = "app"
            applicationId = defaultConfig.applicationId + ".staging"
            versionName = defaultConfig.versionName + " staging"
        }
        create("develop") {
            dimension = "app"
            applicationId = defaultConfig.applicationId + ".develop"
            versionName = defaultConfig.versionName + " develop"
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
    implementation(project(":presentation:view"))
    implementation(project(":presentation:viewmodel"))
    implementation(project(":domain:usecase"))
    implementation(project(":domain:model"))
    implementation(project(":domain:repository"))
    implementation(project(":data:repository"))
    implementation(project(":data:api"))
    implementation(project(":data:cache"))
    implementation(project(":data:resource"))
    implementation(project(":data:mapper"))
    // Dagger
    implementation("com.google.dagger:hilt-android:2.40.3")
    kapt("com.google.dagger:hilt-compiler:2.40.3")
    // AndroidX AppCompat (Need for Dagger-Hilt)
    implementation("androidx.appcompat:appcompat:1.4.0")

    // desugar_jdk_libs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // AndroidX JUnit
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    // AndroidX Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
