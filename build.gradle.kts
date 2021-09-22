// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath(kotlin("gradle-plugin", version = "1.5.31"))
        classpath(kotlin("serialization", version = "1.5.31"))
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
        classpath("com.google.android.gms:oss-licenses-plugin:0.10.4")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
