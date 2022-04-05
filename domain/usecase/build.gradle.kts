import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
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
    // Module
    implementation(projects.domain.model)
    implementation(projects.domain.repository)
    // kotlinx.coroutines
    implementation(libs.kotlinx.coroutines.core)
    // Dagger
    implementation(libs.dagger.hilt.core)
    kapt(libs.dagger.hilt.compiler)
    // StoreFlowable.kt
    implementation(libs.storeflowable.core)

    // JUnit
    testImplementation(libs.junit)
}
