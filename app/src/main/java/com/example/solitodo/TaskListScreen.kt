package com.example.solitodo

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.solitodo.dao.TaskDao
import javax.security.auth.callback.Callback

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(vieModel: TaskVieModel) {
    val tasks = vieModel.tasks.collectAsState(initial = emptyList()).value

    Scaffold (topBar = {
        TopAppBar(title = {Text("Tasks")})
    }, content = { innerpading ->
        LazyColumn(modifier = Modifier.padding(innerpading))
        {
            items(tasks.size){ index ->
                TaskItem(tasks[index]) { vieModel.deleteTask(tasks[index].id) }
            }
        }
    })
}

@Composable
fun TaskItem(task: TaskDao, delete: ()-> Unit)
{
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
                    onClick = { /* Handle edit action */ },
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