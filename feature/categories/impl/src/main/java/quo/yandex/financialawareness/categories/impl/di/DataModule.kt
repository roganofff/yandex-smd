package quo.yandex.financialawareness.categories.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quo.yandex.financialawareness.categories.api.repository.CategoryRepository
import quo.yandex.financialawareness.categories.impl.data.remote.repository.CategoryRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindCategoryRepositoryToImpl(impl: CategoryRepositoryImpl): CategoryRepository
}
