package quo.yandex.financialawareness.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import quo.yandex.financialawareness.BuildConfig
import quo.yandex.financialawareness.domain.api.AccountApi
import quo.yandex.financialawareness.network.interceptor.JwtInterceptor

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Provides
    fun provideOkHttpClient(
        jwtInterceptor: JwtInterceptor
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(jwtInterceptor)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideAccountApi(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): AccountApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MONEYTRACE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()

        return retrofit.create(AccountApi::class.java)
    }
}