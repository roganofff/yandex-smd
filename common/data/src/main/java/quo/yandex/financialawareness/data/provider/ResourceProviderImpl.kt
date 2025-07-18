package quo.yandex.financialawareness.data.provider

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import quo.yandex.financialawareness.domain.provider.ResourceProvider
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {

    override fun getString(stringResId: Int): String {
        return context.getString(stringResId)
    }

    override fun getString(stringResId: Int, vararg args: Any): String {
        return context.getString(stringResId, *args)
    }
}
