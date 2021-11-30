// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath(kotlin("gradle-plugin", version = "1.6.0"))
        classpath(kotlin("serialization", version = "1.6.0"))
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.40.3")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
