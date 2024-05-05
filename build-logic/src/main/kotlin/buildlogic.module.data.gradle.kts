plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
}

kotlin {
    jvmToolchain(libs.findVersion("jdk").get().requiredVersion.toInt())
}

dependencies {
    implementation(platform(libs.findLibrary("ktor.bom").get()))
    implementation(libs.findLibrary("ktor.serialization.json").get())
    implementation(libs.findLibrary("ktor.client.cio").get())
    implementation(libs.findLibrary("ktor.client.content.negotiation").get())

    // Unit Tests
    testImplementation(libs.findLibrary("junit").get())

    // UI Tests
    androidTestImplementation(libs.findLibrary("androidx.test.junit").get())
    androidTestImplementation(libs.findLibrary("androidx.test.espresso").get())
}
