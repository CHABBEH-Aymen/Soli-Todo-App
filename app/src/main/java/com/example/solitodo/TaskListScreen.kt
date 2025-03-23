package com.example.solitodo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.solitodo.dao.TaskDao

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(vieModel: TaskVieModel) {
    val tasks by vieModel.tasks.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var showUpdateDialog by remember { mutableStateOf(false) }
    var updateTask: TaskDao by remember { mutableStateOf(TaskDao( title = "")) }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Tasks") })
    }, content = { innerpading ->
        if (showDialog)
        {
            AddTaskDialog(onDismiss = {showDialog = false}, onCreate = {title ->
                vieModel.createTask(title, "")
                showDialog = false
                vieModel.getTasks()
            })
        }
        if(showUpdateDialog){
            UpdateTaskDialog(onDismiss = { showUpdateDialog = false },
                onUpdate = { id, title ->
                    vieModel.updateTask(id, title)
                }, updateTask )
        }
        LazyColumn(modifier = Modifier.padding(innerpading))
        {
            items(tasks){task ->
                TaskItem(task,
                    delete = { vieModel.deleteTask(task.id!!) },
                    edit = {task ->
                    showUpdateDialog = true
                    updateTask = task
                })
            }
        }
    },
        floatingActionButton = {
            FloatingActionButton(onClick = {showDialog = true}, containerColor = MaterialTheme.colorScheme.primary) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    )
}

@Composable
fun TaskItem(task: TaskDao, delete: () -> Unit, edit: (task: TaskDao)->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    )
    {
        Column(modifier = Modifier.padding(16.dp))
        {
            Text(text = task.title, style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier
                    .fillMaxWidth() // This will make the Row fill the width
                    .padding(top = 8.dp), // Add some space between text and buttons
                horizontalArrangement = Arrangement.SpaceBetween // Distribute the buttons evenly
            ) {
                // Delete Button
                Button(
                    modifier = Modifier.background(Color.Red),
                    onClick = { delete() },
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete, // You can use a delete icon here
                        contentDescription = "Delete"
                    )
                    Text(text = "Delete", style = MaterialTheme.typography.bodySmall)
                }

                // Edit Button
                Button(
                    modifier = Modifier.background(Color.Gray),
                    onClick = { edit(task) },
                    colors = ButtonDefaults.buttonColors(Color.Gray)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit, // You can use an edit icon here
                        contentDescription = "Edit"
                    )
                    Text(text = "Edit", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun AddTaskDialog(onDismiss: ()->Unit, onCreate: (title: String) -> Unit) {
    var taskTitle by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss() })
    {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Add New Task", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    label = { Text("Task Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            if (taskTitle.isNotBlank()) {
                                onCreate(taskTitle) // Pass task title to the callback function
                            }
                        }
                    ) {
                        Text("Add")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}

@Composable
fun UpdateTaskDialog(onDismiss: () -> Unit, onUpdate: (id: Int, title: String)-> Unit, task: TaskDao){
    var taskTitle by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismiss() })
    {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Add New Task", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    label = { Text("Task Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            if (taskTitle.isNotBlank()) {
                                onUpdate(task.id!!, taskTitle)// Pass task to the callback function
                                onDismiss()
                            }
                        }
                    ) {
                        Text("Update")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}