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
    }
}

dependencies {
    // Dagger
    implementation("com.google.dagger:hilt-android:2.41")
    kapt("com.google.dagger:hilt-compiler:2.41")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // AndroidX JUnit
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    // AndroidX Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
