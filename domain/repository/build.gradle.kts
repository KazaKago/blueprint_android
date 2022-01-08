import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
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
    // kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    // StoreFlowable.kt
    implementation("com.kazakago.storeflowable:storeflowable-core:5.2.0")

    // JUnit
    testImplementation("junit:junit:4.13.2")
}
