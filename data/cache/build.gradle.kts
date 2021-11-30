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
    // Dagger
    implementation("com.google.dagger:hilt-core:2.40.3")
    kapt("com.google.dagger:hilt-compiler:2.40.3")
    // StoreFlowable.kt
    implementation("com.kazakago.storeflowable:storeflowable:4.0.0")

    // JUnit
    testImplementation("junit:junit:4.13.2")
}
