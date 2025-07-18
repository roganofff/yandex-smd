package quo.yandex.financialawareness.account.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quo.yandex.financialawareness.account.api.repository.AccountRepository
import quo.yandex.financialawareness.account.impl.data.repository.AccountRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindAccountRepositoryToImpl(impl: AccountRepositoryImpl): AccountRepository
}
