plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
    implementation(libs.google.ksp.gradle.plugin)
    implementation(libs.dagger.hilt.gradle.plugin)
}
