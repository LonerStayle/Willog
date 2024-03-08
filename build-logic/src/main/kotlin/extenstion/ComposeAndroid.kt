package extenstion

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureComposeAndroid(){
    val libs = extensions.libs

    androidExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("composeCompiler").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("androidTestImplementation", platform(bom))

            add("implementation", libs.findLibrary("compose.material3").get())
            add("implementation", libs.findLibrary("compose.ui").get())
            add("implementation", libs.findLibrary("compose.ui.tooling.preview").get())
            add("androidTestImplementation", libs.findLibrary("compose.ui.test").get())
            add("debugImplementation", libs.findLibrary("compose.ui.tooling").get())
            add("debugImplementation", libs.findLibrary("compose.ui.testManifest").get())
        }
    }
}