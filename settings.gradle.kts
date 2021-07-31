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
