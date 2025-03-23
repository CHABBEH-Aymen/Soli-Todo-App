package com.example.solitodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.solitodo.dao.TaskDao
import com.example.solitodo.useCase.CreateTaskUseCase
import com.example.solitodo.useCase.DeleteTaskUseCase
import com.example.solitodo.useCase.GetTasksUseCase
import com.example.solitodo.useCase.UpdateTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.jvm.Throws

class TaskVieModel(
    private val getTasksUseCase: GetTasksUseCase,
    private val deleteTasksUseCase: DeleteTaskUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
): ViewModel() {
    private val _tasks: MutableStateFlow<List<TaskDao>> = MutableStateFlow(emptyList())

    val tasks: MutableStateFlow<List<TaskDao>> get() = _tasks

    init {
        getTasks()
    }

    fun getTasks()
    {
        viewModelScope.launch{
            getTasksUseCase().collect{ taskList ->
                _tasks.value = taskList
            }
        }
    }
    fun createTask(title: String, description: String)
    {
        createTaskUseCase(title, description)
        getTasks()
    }

    fun updateTask(id: Int, title: String)
    {
        updateTaskUseCase(id, title, "empty")
        getTasks()
    }

    fun deleteTask(id: Int)
    {
        deleteTasksUseCase(id)
        getTasks()
    }
}
