plugins {
    id("buildlogic.module.library")
    id("buildlogic.dagger.hilt")
}

android {
    namespace = "com.kazakago.blueprint.data"
}

dependencies {
    implementation(projects.model)
    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.serialization.json)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
}
