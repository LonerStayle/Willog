plugins {
    id("willog.android.feature")
}

android {
    namespace = "kr.loner.feature.photo"
}

dependencies {

    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.compose)
}