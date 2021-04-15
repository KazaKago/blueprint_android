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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // Kotlin
    implementation(kotlin("stdlib-jdk8"))
    // Dagger Hilt
    implementation("com.google.dagger:hilt-core:2.34.1-beta")
    kapt("com.google.dagger:hilt-compiler:2.34.1-beta")
    // StoreFlowable.kt
    implementation("com.github.kazakago.storeflowable:storeflowable:release~3.2.0-SNAPSHOT")

    // JUnit
    testImplementation("junit:junit:4.13.2")
}

