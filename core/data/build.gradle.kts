plugins {
    id("willog.android.library")
    id("willog.hilt.android")
    id("kotlinx-serialization")
}

android {
    namespace = "kr.loner.willog.data"
    defaultConfig {
        /**평소에 local.properties 에 감춰 둡니다. 과제를 위해서 아래 변수에 담았습니다.*/
        buildConfigField("String", "BASE_URL", "\"https://api.unsplash.com\"")
        buildConfigField(
            "String",
            "HEADER_VALUE",
            "\"M1xvAuBdpFJimVeRrsIeSaBG1I1pwhA2blBakSeRSzk\""
        )
    }
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.bundles.network)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)

    kapt(libs.room.compiler)
}