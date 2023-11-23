plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    // Unit Tests
    testImplementation(libs.junit)
    testImplementation(libs.kotest.assertions.core)
}
