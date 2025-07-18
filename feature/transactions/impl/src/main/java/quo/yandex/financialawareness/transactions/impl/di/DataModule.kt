package quo.yandex.financialawareness.transactions.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quo.yandex.financialawareness.transactions.api.repository.TransactionRepository
import quo.yandex.financialawareness.transactions.impl.data.repository.TransactionRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindTransactionRepositoryToImpl(impl: TransactionRepositoryImpl): TransactionRepository
}
