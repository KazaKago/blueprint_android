plugins {
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    implementation(libs.findLibrary("dagger.hilt.android").get())
    ksp(libs.findLibrary("dagger.hilt.compiler").get())
}
