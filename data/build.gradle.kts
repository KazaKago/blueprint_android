plugins {
    id("buildlogic.module.library")
    id("buildlogic.dagger.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.kazakago.blueprint.data"
}

dependencies {
    implementation(projects.domain)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)
    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.serialization.json)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
}
