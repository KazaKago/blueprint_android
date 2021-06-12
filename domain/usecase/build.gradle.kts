import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType(KotlinCompile::class).all {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    // Module
    implementation(project(":domain:model"))
    implementation(project(":domain:repository"))
    // kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    // Dagger
    implementation("com.google.dagger:hilt-core:2.37")
    kapt("com.google.dagger:hilt-compiler:2.37")
    // StoreFlowable.kt
    implementation("com.kazakago.storeflowable:storeflowable-core:3.3.0")

    // JUnit
    testImplementation("junit:junit:4.13.2")
}

