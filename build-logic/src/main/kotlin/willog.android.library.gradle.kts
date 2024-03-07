import extenstion.configureCoroutineAndroid
import extenstion.configureHiltAndroid
import extenstion.configureKotlinAndroid

plugins{
    id("com.android.library")
}
configureKotlinAndroid()
configureCoroutineAndroid()
configureHiltAndroid()