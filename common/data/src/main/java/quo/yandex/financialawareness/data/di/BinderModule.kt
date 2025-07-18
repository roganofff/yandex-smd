package quo.yandex.financialawareness.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quo.yandex.financialawareness.domain.provider.ResourceProvider
import quo.yandex.financialawareness.data.provider.ResourceProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    @Singleton
    fun bindResourceProviderToImpl(impl: ResourceProviderImpl): ResourceProvider
}