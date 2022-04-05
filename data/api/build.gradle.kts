import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlinx.serialization.ExperimentalSerializationApi"
    }
}

dependencies {
    // kotlinx.serialization
    implementation(libs.kotlinx.serialization.json)
    // Dagger
    implementation(libs.dagger.hilt.core)
    kapt(libs.dagger.hilt.compiler)
    // OkHttp
    implementation(libs.okhttp)
    // Retrofit
    implementation(libs.retrofit)
    // Retrofit Serialization Converter
    implementation(libs.retrofit.serialization.converter)

    // JUnit
    testImplementation(libs.junit)
}
