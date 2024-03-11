plugins {
    id("willog.android.library")
}

android{
    namespace = "kr.loner.willog.domain"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
}