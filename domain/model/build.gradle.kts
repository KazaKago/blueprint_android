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
    // JUnit
    testImplementation("junit:junit:4.13.2")
    // Kluent
    testImplementation("org.amshove.kluent:kluent:1.68")
    // MockK
    testImplementation("io.mockk:mockk:1.12.1")
}
