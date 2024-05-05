plugins {
    id("org.jetbrains.kotlin.jvm")
    id("org.jetbrains.kotlin.plugin.serialization")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

kotlin {
    jvmToolchain(libs.findVersion("jdk").get().requiredVersion.toInt())
}

dependencies {
    implementation(libs.findLibrary("kotlinx.serialization.core").get())

    // Unit Tests
    testImplementation(libs.findLibrary("junit").get())
}
