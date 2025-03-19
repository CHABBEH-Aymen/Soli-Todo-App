package com.example.solitodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.solitodo.dao.TaskDao
import com.example.solitodo.useCase.DeleteTaskUseCase
import com.example.solitodo.useCase.GetTasksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.jvm.Throws

class TaskVieModel(private val getTasksUseCase: GetTasksUseCase, private val deleteTasksUseCase: DeleteTaskUseCase): ViewModel() {
    private val _tasks: MutableStateFlow<List<TaskDao>> = MutableStateFlow(emptyList())
    val tasks: MutableStateFlow<List<TaskDao>> get() = _tasks

    init {
        viewModelScope.launch{
            getTasksUseCase.execute().collect{ taskList ->
                _tasks.value = taskList
            }
        }
    }

    fun deleteTask(id: Int)
    {
        deleteTasksUseCase.execute(id)
    }
}

class TaskViewModelFactory(private val getTasksUseCase: GetTasksUseCase, private val deleteTasksUseCase: DeleteTaskUseCase): ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskVieModel::class.java))
            return TaskVieModel(getTasksUseCase, deleteTasksUseCase) as T

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}