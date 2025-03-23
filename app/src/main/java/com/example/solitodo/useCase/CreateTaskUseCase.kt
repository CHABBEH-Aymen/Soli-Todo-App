package com.example.solitodo.useCase

import android.accessibilityservice.GestureDescription
import com.example.solitodo.dao.TaskDao
import com.example.solitodo.repository.TaskRepository

class CreateTaskUseCase(private val taskRepository: TaskRepository) {
    operator fun invoke(title: String, description: String)
    {
        val task = TaskDao(title = title)
        taskRepository.create(task)
    }
}