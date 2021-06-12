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
    // Dagger
    implementation("com.google.dagger:hilt-core:2.37")
    kapt("com.google.dagger:hilt-compiler:2.37")
    // StoreFlowable.kt
    implementation("com.kazakago.storeflowable:storeflowable:3.3.0")

    // JUnit
    testImplementation("junit:junit:4.13.2")
}

