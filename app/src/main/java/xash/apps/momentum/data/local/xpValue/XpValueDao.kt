package xash.apps.momentum.data.local.xpValue

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// DAO
@Dao
interface XpDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertXp(xp: XpEntity)

    @Query("SELECT * FROM xp_table WHERE id = 1")
    suspend fun getXp(): XpEntity?
}