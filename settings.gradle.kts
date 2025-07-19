pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FinancialAwareness"
include(":app")
include(":common")
include(":common:navigation")
include(":common:domain")
include(":common:util")
include(":common:network")
include(":common:data")
include(":common:ui")
include(":feature")
include(":feature:account")
include(":feature:categories")
include(":feature:expenses")
include(":feature:income")
include(":feature:settings")
include(":feature:transactions")
include(":feature:account:api")
include(":feature:account:impl")
include(":feature:categories:impl")
include(":feature:categories:api")
include(":feature:expenses:impl")
include(":feature:income:impl")
include(":feature:settings:impl")
include(":feature:transactions:api")
include(":feature:transactions:impl")
include(":feature:analysis")
include(":feature:analysis:impl")
