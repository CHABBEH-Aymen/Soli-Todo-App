package com.example.solitodo.useCase

import com.example.solitodo.repository.TaskRepository

class DeleteTaskUseCase(private val taskRepository: TaskRepository ) {

    fun execute(id: Int)
    {
        taskRepository.delete(id)
    }
}