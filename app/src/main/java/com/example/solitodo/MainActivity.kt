package com.example.solitodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.solitodo.network.ApiService
import com.example.solitodo.repository.TaskRepository
import com.example.solitodo.useCase.DeleteTaskUseCase
import com.example.solitodo.useCase.GetTasksUseCase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository: TaskRepository = TaskRepository(ApiService)
        val getTaskUseCase = GetTasksUseCase(repository)
        val deleteTaskUseCase = DeleteTaskUseCase(repository)
        val vieModel = TaskVieModel(getTaskUseCase, deleteTaskUseCase)
        enableEdgeToEdge()
        setContent {
//            SoliTodoTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
            TaskListScreen(vieModel)
        }
    }
}
