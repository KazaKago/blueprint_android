plugins {
    id("buildlogic.module.application")
    id("buildlogic.dagger.hilt")
}

android {
    namespace = "com.kazakago.blueprint"
    defaultConfig {
        applicationId = "com.kazakago.blueprint"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
    }
}

dependencies {
    implementation(projects.presentation)
    implementation(projects.domain)
    implementation(projects.data)
}
