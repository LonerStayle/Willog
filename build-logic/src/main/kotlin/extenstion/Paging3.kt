package extenstion

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configurePaging3() {
    val libs = extensions.libs
    dependencies {
        add("implementation", libs.findLibrary("room.paging").get())
    }
}