plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.android.gms.oss-licenses-plugin")
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "com.kazakago.blueprint"
        minSdk = 28
        targetSdk = 30
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = " debug"
        }
    }
    flavorDimensions("app")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
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
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    // Dagger
    implementation("com.google.dagger:hilt-android:2.35.1")
    kapt("com.google.dagger:hilt-compiler:2.35.1")
    // AndroidX AppCompat
    implementation("androidx.appcompat:appcompat:1.2.0")

    // desugar_jdk_libs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // Espresso
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
