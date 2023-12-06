plugins {
    id("buildlogic.module.library")
}

android {
    namespace = "com.kazakago.blueprint.mockserver"
}

dependencies {
    implementation(projects.model)
    implementation("androidx.startup:startup-runtime:1.1.1")
    implementation(platform("io.ktor:ktor-bom:2.3.7"))
    implementation("io.ktor:ktor-serialization-kotlinx-json")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-server-content-negotiation")
}
