pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "willog"
include(":app")
include(":data")
include(":domain")
include(":ui")
include(":ui:core")
include(":ui:core:disiginsystem")
include(":ui:feature")
include(":ui:core:core-ui")
