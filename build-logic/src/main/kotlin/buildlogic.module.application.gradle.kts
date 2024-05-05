plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("buildlogic.dagger.hilt")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
    compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()
    defaultConfig {
        minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
        targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/*"
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
