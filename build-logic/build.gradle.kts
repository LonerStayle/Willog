plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}


dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.android.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("hilt-android") {
            id = "willog.hilt.android"
            implementationClass = "extenstion.HiltAndroidPlugin"
        }
    }
    plugins {
        register("hilt-kotlin") {
            id = "willog.hilt.kotlin"
            implementationClass = "extenstion.HiltKotlinPlugin"
        }
    }
}