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
    // kotlinx-datetime
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
    // Dagger
    implementation("com.google.dagger:hilt-core:2.40.5")
    kapt("com.google.dagger:hilt-compiler:2.40.5")
    // StoreFlowable.kt
    implementation("com.kazakago.storeflowable:storeflowable:5.2.0")

    // JUnit
    testImplementation("junit:junit:4.13.2")
}
