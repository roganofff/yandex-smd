package quo.yandex.financialawareness.transactions.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quo.yandex.financialawareness.transactions.api.usecase.GetTransactionsByPeriodUseCase
import quo.yandex.financialawareness.transactions.impl.domain.usecase.GetTransactionsByPeriodUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    @Singleton
    fun bindGetTransactionsByPeriodUseCaseToImpl(impl: GetTransactionsByPeriodUseCaseImpl): GetTransactionsByPeriodUseCase

}