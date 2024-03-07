plugins {
    id("willog.android.library")
    id("willog.hilt.android")
}

android {
    namespace = "kr.loner.willog.data"
}

dependencies {
    implementation(libs.bundles.network)
}