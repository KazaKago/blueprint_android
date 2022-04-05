import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
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
