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
include(":core:data")
include(":core:domain")
include(":core:designsystem")
include(":core:model")
include(":feature:main")
include(":feature:photo")
include(":core:testing")
