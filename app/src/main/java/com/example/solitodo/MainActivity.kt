package com.example.solitodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.key
import com.example.solitodo.network.ApiService
import com.example.solitodo.repository.TaskRepository
import com.example.solitodo.useCase.CreateTaskUseCase
import com.example.solitodo.useCase.DeleteTaskUseCase
import com.example.solitodo.useCase.GetTasksUseCase
import com.example.solitodo.useCase.UpdateTaskUseCase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository: TaskRepository = TaskRepository(ApiService)
        val getTaskUseCase = GetTasksUseCase(repository)
        val deleteTaskUseCase = DeleteTaskUseCase(repository)
        val createTaskUseCase = CreateTaskUseCase(repository)
        val updateTaskUseCase = UpdateTaskUseCase(repository)
        val vieModel = TaskVieModel(getTaskUseCase, deleteTaskUseCase, createTaskUseCase, updateTaskUseCase)
        enableEdgeToEdge()
        setContent {
            TaskListScreen(vieModel)
        }
    }
}
