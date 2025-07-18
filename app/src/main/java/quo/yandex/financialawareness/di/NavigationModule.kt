package quo.yandex.financialawareness.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import quo.yandex.financialawareness.navigation.NavigationManager
import quo.yandex.financialawareness.navigation.NavigationManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavigationManagerToImpl(impl: NavigationManagerImpl): NavigationManager
}
