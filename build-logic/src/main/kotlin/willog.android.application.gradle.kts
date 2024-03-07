import extenstion.configureHiltAndroid
import extenstion.configureKotlinAndroid

plugins{
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()