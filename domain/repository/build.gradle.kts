import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType(KotlinCompile::class).all {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // Module
    implementation(project(":domain:model"))
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    // kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    // StoreFlowable.kt
    implementation("com.kazakago.storeflowable:storeflowable-core:3.2.0")

    // JUnit
    testImplementation("junit:junit:4.13.2")
}

