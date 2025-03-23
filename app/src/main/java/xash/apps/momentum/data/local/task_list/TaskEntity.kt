package xash.apps.momentum.data.local.task_list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val isDone: Boolean = false,
    val timestamp: Long = System.currentTimeMillis()
)
