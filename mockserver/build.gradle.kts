plugins {
    id("buildlogic.module.library")
}

android {
    namespace = "com.kazakago.blueprint.mockserver"
}

dependencies {
    implementation(projects.model)
    implementation(libs.androidx.startup)
    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.serialization.json)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)
}
