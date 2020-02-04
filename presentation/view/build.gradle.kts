import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    flavorDimensions("app")
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
        coreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    lintOptions {
        xmlReport = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //Module
    implementation(project(":presentation:viewmodel"))
    implementation(project(":domain:model"))
    //Kotlin
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
    //Koin
    implementation("org.koin:koin-androidx-viewmodel:2.0.1")
    //AndroidX KTX
    implementation("androidx.fragment:fragment-ktx:1.2.0")
    //AndroidX AppCompat
    api("androidx.appcompat:appcompat:1.1.0")
    //AndroidX ConstraintLayout
    api("androidx.constraintlayout:constraintlayout:1.1.3")
    //AndroidX Material
    api("com.google.android.material:material:1.1.0")
    //AndroidX Lifecycle
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    api("androidx.lifecycle:lifecycle-common-java8:2.2.0")
    //PlayServices OSS Licenses
    implementation("com.google.android.gms:play-services-oss-licenses:17.0.0")
    //Groupie
    implementation("com.xwray:groupie:2.7.2")
    //Coil
    implementation("io.coil-kt:coil:0.9.3")

    //desugar_jdk_libs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.0.4")

    //JUnit
    testImplementation("junit:junit:4.13")

    //Espresso
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
