package quo.yandex.financialawareness

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FinancialApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}