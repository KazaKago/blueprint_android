import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(28)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("proguard-rules.pro")
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    lintOptions {
        xmlReport = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //Module
    implementation(project(":domain"))
    //Kotlin
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    //kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1")
    //Koin
    implementation("org.koin:koin-core:1.0.2")
    //AndroidX Room
    implementation("androidx.room:room-runtime:2.1.0-alpha04")
    implementation("androidx.room:room-coroutines:2.1.0-alpha04")
    kapt("androidx.room:room-compiler:2.1.0-alpha04")
    //AndroidX KTX
    implementation("androidx.core:core-ktx:1.0.1")
    //Moshi
    implementation("com.squareup.moshi:moshi:1.8.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.8.0")

    //JUnit
    testImplementation("junit:junit:4.12")

    //Espresso
    androidTestImplementation("androidx.test:runner:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1")
}
