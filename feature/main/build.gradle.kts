plugins {
    id("willog.android.feature")
}

android {
    namespace = "kr.loner.willog.feature.main"
}

dependencies {

    implementation(libs.android.core.ktx)
    implementation(libs.activity.compose)
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation(libs.androidx.paging.runtime.ktx)

}