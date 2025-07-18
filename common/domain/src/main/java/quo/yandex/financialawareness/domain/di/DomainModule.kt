package quo.yandex.financialawareness.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import quo.yandex.financialawareness.domain.di.qualifier.DefaultDispatchers
import quo.yandex.financialawareness.domain.di.qualifier.IoDispatchers

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @IoDispatchers
    fun provideIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @DefaultDispatchers
    fun provideDefaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }
}