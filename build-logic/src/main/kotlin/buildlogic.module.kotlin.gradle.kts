plugins {
    id("org.jetbrains.kotlin.jvm")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

kotlin {
    jvmToolchain(libs.findVersion("java").get().requiredVersion.toInt())
}

dependencies {
    // Unit Tests
    testImplementation(libs.findLibrary("junit").get())
}
