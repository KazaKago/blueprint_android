plugins {
    alias(libs.plugins.buildlogic.module.ui)
}

android {
    namespace = "com.kazakago.blueprint.ui"
}

dependencies {
    implementation(projects.data)
    implementation(projects.model)
}
