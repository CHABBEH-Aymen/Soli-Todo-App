package com.example.solitodo.useCase

import com.example.solitodo.dao.TaskDao
import com.example.solitodo.repository.TaskRepository

class UpdateTaskUseCase(private val taskRepository: TaskRepository) {

    operator fun invoke(id: Int, title: String, description: String)
    {
        val task = TaskDao(title = title)
        taskRepository.update(id, task)
    }
}