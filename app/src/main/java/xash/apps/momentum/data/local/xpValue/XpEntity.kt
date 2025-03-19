package xash.apps.momentum.data.local.xpValue

import androidx.room.Entity
import androidx.room.PrimaryKey

// Room Entity
@Entity(tableName = "xp_table")
data class XpEntity(@PrimaryKey val id: Int = 1, val xp: Int)