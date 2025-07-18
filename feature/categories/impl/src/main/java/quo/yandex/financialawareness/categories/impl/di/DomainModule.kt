package quo.yandex.financialawareness.categories.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quo.yandex.financialawareness.categories.api.usecase.GetCategoriesUseCase
import quo.yandex.financialawareness.categories.impl.domain.usecase.GetCategoriesUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    @Singleton
    fun bindGetCategoriesUseCaseToImpl(impl: GetCategoriesUseCaseImpl): GetCategoriesUseCase

}