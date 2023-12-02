plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.findVersion("compose.compiler").get().toString()
    }
}

dependencies {
    implementation(platform(libs.findLibrary("androidx.compose.bom").get()))
    implementation(libs.findLibrary("androidx.compose.material3").get())
    implementation(libs.findLibrary("androidx.compose.material.icons.extended").get())
    implementation(libs.findLibrary("androidx.activity.compose").get())
    implementation(libs.findLibrary("coil.compose").get())
    implementation(libs.findLibrary("swr.compose").get())
    implementation(libs.findLibrary("compose.distinations.core").get())
    ksp(libs.findLibrary("compose.distinations.ksp").get())

    // Android Studio Preview support
    implementation(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
    debugImplementation(libs.findLibrary("androidx.compose.ui.tooling").get())

    // UI Tests
    androidTestImplementation(platform(libs.findLibrary("androidx.compose.bom").get()))
    androidTestImplementation(libs.findLibrary("androidx.compose.ui.test.junit4").get())
    debugImplementation(libs.findLibrary("androidx.compose.ui.test.manifest").get())
}
