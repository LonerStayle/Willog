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
//    implementation(libs.navigation.compose)

    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation(libs.paging.runtime.ktx)

}