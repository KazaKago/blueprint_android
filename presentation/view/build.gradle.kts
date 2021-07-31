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
    // Dagger
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-compiler:2.38.1")
    // AndroidX AppCompat
    implementation("androidx.appcompat:appcompat:1.3.1")
    // AndroidX Activity
    implementation("androidx.activity:activity-ktx:1.3.0")
    // AndroidX Fragment
    implementation("androidx.fragment:fragment-ktx:1.3.6")
    // AndroidX ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    // AndroidX RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    // AndroidX SwipeRefreshLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    // Material Components
    implementation("com.google.android.material:material:1.4.0")
    // PlayServices OSS Licenses
    implementation("com.google.android.gms:play-services-oss-licenses:17.0.0")
    // Groupie
    implementation("com.github.lisawray.groupie:groupie:2.9.0")
    implementation("com.github.lisawray.groupie:groupie-viewbinding:2.9.0")
    // Coil
    implementation("io.coil-kt:coil:1.3.0")

    // desugar_jdk_libs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // Espresso
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
