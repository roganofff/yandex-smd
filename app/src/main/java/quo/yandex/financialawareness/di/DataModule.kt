package quo.yandex.financialawareness.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quo.yandex.financialawareness.data.repository.AccountRepositoryImpl
import quo.yandex.financialawareness.domain.repository.AccountRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindAccountRepositoryToImpl(impl: AccountRepositoryImpl): AccountRepository
}