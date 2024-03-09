import extenstion.configureCoroutineAndroid
import extenstion.configureHiltAndroid
import extenstion.configureKotlinAndroid
import extenstion.configurePaging3

plugins{
    id("com.android.library")
}

configureKotlinAndroid()
configureCoroutineAndroid()
configureHiltAndroid()

configurePaging3()