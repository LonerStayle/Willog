package extenstion

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHiltKotlin() {
    with(pluginManager) {
        apply("org.jetbrains.kotlin.kapt")
    }

    val libs = extensions.libs

    dependencies {
        add("implementation", libs.findLibrary("hilt.core").get())
        add("kapt", libs.findLibrary("hilt.compiler").get())
    }
}

internal class HiltKotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.configureHiltKotlin()
    }
}