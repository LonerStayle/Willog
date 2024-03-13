import extenstion.configureHiltAndroid
import extenstion.libs

plugins {
    id("willog.android.library")
    id("willog.android.compose")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configureHiltAndroid()

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:designsystem"))

    val libs = project.extensions.libs

    implementation(libs.findLibrary("hilt-navigation-compose").get())
    implementation(libs.findLibrary("navigation-compose").get())
//    androidTestImplementation(libs.findLibrary("androidx.compose.navigation.test").get())

    implementation(libs.findLibrary("lifecycle-viewmodel-compose").get())
    implementation(libs.findLibrary("lifecycle-runtime-compose").get())
}