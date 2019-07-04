import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.android.gms.oss-licenses-plugin")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "com.kazakago.cleanarchitecture"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            applicationIdSuffix = ".debug"
            versionNameSuffix = " debug"
        }
    }
    flavorDimensions("app")
    productFlavors {
        create("production") {
            setDimension("app")
        }
        create("staging") {
            setDimension("app")
            applicationId = defaultConfig.applicationId + ".staging"
            versionName = defaultConfig.versionName + " staging"
        }
        create("develop") {
            setDimension("app")
            applicationId = defaultConfig.applicationId + ".develop"
            versionName = defaultConfig.versionName + " develop"
        }
    }
    lintOptions {
        xmlReport = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //Module
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":web"))
    implementation(project(":data"))
    //Kotlin
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))
    //Koin for Android
    implementation("org.koin:koin-android:1.0.2")

    //Stetho
    debugImplementation("com.facebook.stetho:stetho:1.5.1")
    releaseImplementation("com.github.mypplication:stetho-noop:1.1")
    //LeakCanary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.0-alpha-2")
    //Hyperion
    debugImplementation("com.willowtreeapps.hyperion:hyperion-core:0.9.27")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-attr:0.9.27")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-measurement:0.9.27")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-disk:0.9.27")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-recorder:0.9.27")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-phoenix:0.9.27")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-crash:0.9.27")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-shared-preferences:0.9.27")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-geiger-counter:0.9.27")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-timber:0.9.27")
    debugImplementation("com.willowtreeapps.hyperion:hyperion-build-config:0.9.27")
    //Hyperion-Chuck
    debugImplementation("com.github.Commit451:Hyperion-Chuck:1.0.0")

    //JUnit
    testImplementation("junit:junit:4.12")

    //Espresso
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}
