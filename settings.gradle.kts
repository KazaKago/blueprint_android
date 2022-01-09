pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "dagger.hilt.android.plugin") {
                useModule("com.google.dagger:hilt-android-gradle-plugin:${requested.version}")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
}
rootProject.name = "blueprint"
include(":app")
include(":presentation:view")
include(":presentation:viewmodel")
include(":domain:repository")
include(":domain:model")
include(":domain:usecase")
include(":data:resource")
include(":data:repository")
include(":data:api")
include(":data:cache")
include(":data:mapper")
