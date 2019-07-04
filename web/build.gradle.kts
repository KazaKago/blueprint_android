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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.2")
    //Koin
    implementation("org.koin:koin-core:1.0.2")
    //AndroidX KTX
    implementation("androidx.core:core-ktx:1.0.2")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.6.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.6.0")
    //Moshi
    implementation("com.squareup.moshi:moshi:1.8.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.8.0")

    //Stetho
    debugImplementation("com.facebook.stetho:stetho-okhttp3:1.5.1")
    releaseImplementation("com.github.mypplication:stetho-noop:1.1")
    //Chuck
    debugImplementation("com.readystatesoftware.chuck:library:1.1.0")
    releaseImplementation("com.readystatesoftware.chuck:library-no-op:1.1.0")

    //JUnit
    testImplementation("junit:junit:4.12")

    //Espresso
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}