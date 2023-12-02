import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.implementation(artifact: Provider<MinimalExternalModuleDependency>) {
    add("implementation", artifact)
}
