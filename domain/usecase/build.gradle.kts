import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

dependencies {
    // Module
    implementation(project(":domain:model"))
    implementation(project(":domain:repository"))
    // kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    // Dagger
    implementation("com.google.dagger:hilt-core:2.39.1")
    kapt("com.google.dagger:hilt-compiler:2.39.1")
    // StoreFlowable.kt
    implementation("com.kazakago.storeflowable:storeflowable-core:4.0.0")

    // JUnit
    testImplementation("junit:junit:4.13.2")
}
