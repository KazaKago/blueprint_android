pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "blueprint"
include(":app")
include(":presentation:controller")
include(":presentation:ui")
include(":presentation:uistate")
include(":domain:usecase")
include(":domain:model")
include(":domain:repository")
include(":data:repository")
include(":data:mapper")
include(":data:api")
include(":data:cache")
include(":data:resource")

// https://docs.gradle.org/current/userguide/declaring_dependencies.html#sec:type-safe-project-accessors
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
