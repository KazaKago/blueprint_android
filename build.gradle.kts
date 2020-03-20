// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0-beta03")
        classpath(kotlin("gradle-plugin", "1.3.70"))
        classpath("com.google.android.gms:oss-licenses-plugin:0.10.2")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.2.1")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}