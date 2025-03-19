package xash.apps.momentum.data.local.xpValue

import kotlinx.coroutines.flow.MutableStateFlow

// Singleton Repository with MutableStateFlow
object XpRepository {
    private lateinit var db: XpDatabase
    private val _xpFlow = MutableStateFlow(0)
    val xpFlow = _xpFlow

    fun initDatabase(database: XpDatabase) {
        db = database
    }

    suspend fun loadXp() {
        _xpFlow.value = db.xpDao().getXp()?.xp ?: 0
    }

    suspend fun updateXp(newXp: Int) {
        db.xpDao().insertXp(XpEntity(xp = newXp))
        _xpFlow.value = newXp
    }
}