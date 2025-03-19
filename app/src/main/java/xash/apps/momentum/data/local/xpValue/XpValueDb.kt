package xash.apps.momentum.data.local.xpValue

import androidx.room.Database
import androidx.room.RoomDatabase

// Database
@Database(entities = [XpEntity::class], version = 1)
abstract class XpDatabase : RoomDatabase() {
    abstract fun xpDao(): XpDao
}