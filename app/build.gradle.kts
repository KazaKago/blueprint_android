import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.android.gms.oss-licenses-plugin")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.kazakago.cleanarchitecture"
        minSdkVersion(26)
        targetSdkVersion(30)
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
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    //Module
    implementation(project(":presentation:view"))
    implementation(project(":presentation:viewmodel"))
    implementation(project(":domain:usecase"))
    implementation(project(":domain:model"))
    implementation(project(":domain:repository"))
    implementation(project(":data:repository"))
    implementation(project(":data:api"))
    implementation(project(":data:database"))
    implementation(project(":data:resource"))
    implementation(project(":data:memory"))
    //Kotlin
    implementation(kotlin("stdlib-jdk8", KotlinCompilerVersion.VERSION))
    //Koin for Android
    implementation("org.koin:koin-android:2.2.2")

    //desugar_jdk_libs
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    //Flipper
    debugImplementation("com.facebook.flipper:flipper:0.84.0")
    debugImplementation("com.facebook.flipper:flipper-network-plugin:0.84.0")
    debugImplementation("com.facebook.soloader:soloader:0.10.1")
    //LeakCanary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.7")

    //JUnit
    testImplementation("junit:junit:4.13.2")

    //Espresso
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}
