import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    setSourceCompatibility(libs.versions.java.get())
    setTargetCompatibility(libs.versions.java.get())
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = libs.versions.java.get()
    }
}

dependencies {
    // Kotlinx Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // JUnit
    testImplementation(libs.junit)
    // Kotest
    testImplementation(libs.kotest.assertions.core)
}