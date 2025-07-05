package quo.yandex.financialawareness.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quo.yandex.financialawareness.data.provider.ResourceProviderImpl
import quo.yandex.financialawareness.di.provider.ResourceProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BinderModule {

    @Binds
    @Singleton
    fun bindResourceProviderToImpl(impl: ResourceProviderImpl): ResourceProvider
}