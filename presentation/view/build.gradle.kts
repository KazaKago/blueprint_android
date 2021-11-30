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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Module
    implementation(project(":presentation:viewmodel"))
    implementation(project(":domain:model"))
    // kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC")
    // Dagger
    implementation("com.google.dagger:hilt-android:2.40.3")
    kapt("com.google.dagger:hilt-compiler:2.40.3")
    // AndroidX AppCompat
    implementation("androidx.appcompat:appcompat:1.4.0")
    // AndroidX Activity
    implementation("androidx.activity:activity-ktx:1.4.0")
    // AndroidX Fragment
    implementation("androidx.fragment:fragment-ktx:1.4.0")
    // AndroidX ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    // AndroidX RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    // AndroidX SwipeRefreshLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    // AndroidX Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.4.0")
    // Material Components
    implementation("com.google.android.material:material:1.4.0")
    // PlayServices OSS Licenses
    implementation("com.google.android.gms:play-services-oss-licenses:17.0.0")
    // Groupie
    implementation("com.github.lisawray.groupie:groupie:2.10.0")
    implementation("com.github.lisawray.groupie:groupie-viewbinding:2.10.0")
    // Coil
    implementation("io.coil-kt:coil:1.4.0")

    // desugar_jdk_libs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // AndroidX JUnit
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    // AndroidX Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
