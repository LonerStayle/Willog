plugins {
    id("willog.android.application")
}

android {
    namespace = "kr.loner.willog"

    defaultConfig {
        applicationId = "kr.loner.willog"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes{
        getByName("release"){
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

}