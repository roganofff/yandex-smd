package quo.yandex.financialawareness.transactions.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import quo.yandex.financialawareness.network.BuildConfig
import quo.yandex.financialawareness.transactions.impl.data.remote.TransactionApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideTransactionApi(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): TransactionApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.MONEYTRACE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()

        return retrofit.create(TransactionApi::class.java)
    }
}