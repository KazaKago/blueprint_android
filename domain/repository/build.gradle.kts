import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.jvm)
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
    // kotlinx.coroutines
    implementation(libs.kotlinx.coroutines.core)
    // StoreFlowable.kt
    implementation(libs.storeflowable.core)

    // JUnit
    testImplementation(libs.junit)
}
