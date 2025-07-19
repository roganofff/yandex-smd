package quo.yandex.financialawareness.database

import androidx.room.Database
import androidx.room.RoomDatabase
import quo.yandex.financialawareness.account.impl.data.local.dao.AccountDao
import quo.yandex.financialawareness.account.impl.data.local.entity.AccountCategoryDetailsEntity
import quo.yandex.financialawareness.account.impl.data.local.entity.AccountDetailsEntity
import quo.yandex.financialawareness.account.impl.data.local.entity.AccountEntity
import quo.yandex.financialawareness.categories.impl.data.local.dao.CategoryDao
import quo.yandex.financialawareness.categories.impl.data.local.entity.CategoryEntity
import quo.yandex.financialawareness.transactions.impl.data.local.dao.TransactionDao
import quo.yandex.financialawareness.transactions.impl.data.local.entity.TransactionEntity

@Database(
    entities = [AccountEntity::class,
        AccountCategoryDetailsEntity::class,
        AccountDetailsEntity::class,
        CategoryEntity::class,
        TransactionEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
    abstract fun accountDao(): AccountDao
}