plugins {
    alias(libs.plugins.buildlogic.module.data)
}

android {
    namespace = "com.kazakago.blueprint.data"
}

dependencies {
    implementation(projects.model)
}
