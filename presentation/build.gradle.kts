plugins {
    id("buildlogic.module.library")
    id("buildlogic.compose")
    id("buildlogic.dagger.hilt")
}

android {
    namespace = "com.kazakago.blueprint.presentation"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.data)
    implementation(libs.androidx.lifecycle.runtime.compose)
}
