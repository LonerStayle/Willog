package extenstion

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCoroutineAndroid() {
    val libs = extensions.libs
    configureCoroutine()
    dependencies { "implementation"(libs.findLibrary("coroutines.android").get()) }
}

private fun Project.configureCoroutine() {
    val libs = extensions.libs

    dependencies {
        add("implementation", libs.findLibrary("coroutines.core").get())
        add("testImplementation", libs.findLibrary("coroutines.test").get())
    }
}