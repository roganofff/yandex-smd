package quo.yandex.financialawareness.account.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import quo.yandex.financialawareness.account.impl.data.remote.AccountApi
import quo.yandex.financialawareness.network.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

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