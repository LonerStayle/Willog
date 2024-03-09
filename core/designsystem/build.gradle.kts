plugins {
    id("willog.android.library")
    id("willog.android.compose")
}

android {
    namespace = "kr.loner.willog.designsystem"
}

dependencies {
    implementation(libs.appcompat)
}