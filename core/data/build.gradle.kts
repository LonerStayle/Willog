plugins {
    id("willog.android.library")
    id("willog.hilt.android")
}

android {
    namespace = "kr.loner.willog.data"
}

dependencies {
    implementation(libs.bundles.network)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    kapt(libs.room.compiler)

}