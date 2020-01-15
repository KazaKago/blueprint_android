// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0-alpha08")
        classpath(kotlin("gradle-plugin", "1.3.61"))
        classpath("com.google.android.gms:oss-licenses-plugin:0.10.1")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}