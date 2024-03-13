plugins {
    id("willog.android.feature")
}

android {
    namespace = "kr.loner.feature.photo"

    //페이징3 - 컴포즈 버그 원인을 찾지 못해서 xml 급 사용..
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.compose)

    //과제 시간 얼마 안남아.. 임시 하드코딩,,ㅠㅠ
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
}