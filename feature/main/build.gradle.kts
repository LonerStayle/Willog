plugins {
    id("willog.android.feature")
}

android {
    namespace = "kr.loner.willog.feature.main"
}

dependencies {
    implementation(project(":feature:photo"))

    implementation(libs.android.core.ktx)
    implementation(libs.activity.compose)


}