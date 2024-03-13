plugins {
    id("willog.android.library")
}

android {
    namespace = "kr.loner.core.testing"
}

dependencies {
    api(libs.coroutines.test)
    api(libs.junit)
    api(libs.mockk)
    api(libs.kotlin.test)
    api(libs.turbine)
}