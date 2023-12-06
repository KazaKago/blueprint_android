plugins {
    id("buildlogic.module.kotlin")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.core)
}
