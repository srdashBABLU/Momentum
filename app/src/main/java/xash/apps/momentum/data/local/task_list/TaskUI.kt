package xash.apps.momentum.data.local.task_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun TaskScreen(database: TaskDatabase) {
    val dao = database.taskDao()
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val tasks by dao.getAllTasks().collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    coroutineScope.launch {
                        dao.insertTask(Task(title = title, description = description))
                    }
                    title = ""
                    description = ""
                }
            },
            modifier = Modifier.padding(top = 8.dp).fillMaxWidth()
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                TaskItem(task = task, dao = dao)
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, dao: TaskDao) {
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title)
            Text(text = task.description)

            Row {
                Checkbox(
                    checked = task.isDone,
                    onCheckedChange = {
                        coroutineScope.launch {
                            dao.updateTask(task.copy(isDone = it))
                        }
                    }
                )
                Text("Done?")
            }

            Button(onClick = { coroutineScope.launch { dao.deleteTask(task) } }) {
                Text("Delete")
            }
        }
    }
}
