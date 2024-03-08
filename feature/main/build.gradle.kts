plugins {
    id("willog.android.feature")
}

android {
    namespace = "kr.loner.willog.feature.main"
}

dependencies {
    implementation(libs.android.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity.compose)
}