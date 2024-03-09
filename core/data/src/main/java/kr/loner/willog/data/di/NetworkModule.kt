package kr.loner.willog.data.di


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kr.loner.willog.data.BuildConfig
import kr.loner.willog.data.remote.api.PhotoApi
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private val jsonRule = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    private val jsonConverterFactory = jsonRule.asConverterFactory(
        "application/json".toMediaType()
    )

    @Singleton
    @Provides
    internal fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }


    @Singleton
    @Provides
    internal fun provideHeaderInterceptor() = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader(
                name = "Authorization",
                value = "Client-ID ${BuildConfig.HEADER_VALUE}"
            )
            .build()
        chain.proceed(newRequest)
    }

    @Singleton
    @Provides
    fun providePhotoApi(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): PhotoApi {
        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(jsonConverterFactory)
            .client(client)
            .build()
            .create(PhotoApi::class.java)

    }


}