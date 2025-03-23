package com.example.solitodo.useCase

import com.example.solitodo.dao.TaskDao
import com.example.solitodo.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(private val taskRepository: TaskRepository){
    operator fun invoke(): Flow<List<TaskDao>>
    {
        return taskRepository.tasks;
    }
}