plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("buildlogic.dagger.hilt")
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
    buildFeatures {
        compose = true
    }
}

kotlin {
    jvmToolchain(libs.findVersion("jdk").get().requiredVersion.toInt())
}

composeCompiler {
    enableStrongSkippingMode = true
}

dependencies {
    implementation(libs.findLibrary("kotlinx.serialization.core").get())
    implementation(platform(libs.findLibrary("androidx.compose.bom").get()))
    implementation(libs.findLibrary("androidx.compose.material3").get())
    implementation(libs.findLibrary("androidx.compose.material.icons.extended").get())
    implementation(libs.findLibrary("androidx.activity.compose").get())
    implementation(libs.findLibrary("androidx.navigation.compose").get())
    implementation(libs.findLibrary("coil.compose").get())
    implementation(libs.findLibrary("swr.compose").get())

    // Android Studio Preview support
    implementation(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
    debugImplementation(libs.findLibrary("androidx.compose.ui.tooling").get())

    // Unit Tests
    testImplementation(libs.findLibrary("junit").get())

    // UI Tests
    androidTestImplementation(libs.findLibrary("androidx.test.junit").get())
    androidTestImplementation(libs.findLibrary("androidx.test.espresso").get())
    androidTestImplementation(platform(libs.findLibrary("androidx.compose.bom").get()))
    androidTestImplementation(libs.findLibrary("androidx.compose.ui.test.junit4").get())
    debugImplementation(libs.findLibrary("androidx.compose.ui.test.manifest").get())
}
