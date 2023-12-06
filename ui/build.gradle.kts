plugins {
    id("buildlogic.module.library")
    id("buildlogic.dagger.hilt")
    id("buildlogic.compose")
}

android {
    namespace = "com.kazakago.blueprint.ui"
}

dependencies {
    implementation(projects.data)
    implementation(projects.model)
}
