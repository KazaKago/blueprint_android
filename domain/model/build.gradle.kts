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
    // JUnit
    testImplementation("junit:junit:4.13.2")
    // Kluent
    testImplementation("org.amshove.kluent:kluent:1.65")
    // MockK
    testImplementation("io.mockk:mockk:1.11.0")
}

