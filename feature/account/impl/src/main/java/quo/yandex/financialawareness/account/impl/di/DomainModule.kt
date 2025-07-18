package quo.yandex.financialawareness.account.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quo.yandex.financialawareness.account.api.usecase.GetAccountUseCase
import quo.yandex.financialawareness.account.impl.domain.usecase.GetAccountUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    @Singleton
    fun bindGetAccountUseCaseToImpl(impl: GetAccountUseCaseImpl): GetAccountUseCase
}