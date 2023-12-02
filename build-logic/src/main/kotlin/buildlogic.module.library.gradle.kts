plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()
    defaultConfig {
        minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

kotlin {
    jvmToolchain(libs.findVersion("java").get().requiredVersion.toInt())
}

dependencies {
    // Unit Tests
    testImplementation(libs.findLibrary("junit").get())

    // UI Tests
    androidTestImplementation(libs.findLibrary("androidx.test.junit").get())
    androidTestImplementation(libs.findLibrary("androidx.test.espresso").get())
}
