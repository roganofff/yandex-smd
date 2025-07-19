package quo.yandex.financialawareness.analysis.impl.ui.nav

import quo.yandex.financialawareness.navigation.NavGraph
import quo.yandex.financialawareness.transactions.api.model.TransactionType

class AnalysisNavGraph : NavGraph() {
    override val route = "analysis"
    override val startDestination = "analysis_main_screen/{transactionType}"

    fun mainRoute(transactionType: TransactionType) = "analysis_main_screen/${transactionType.name}"
}