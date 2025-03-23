package com.example.solitodo.useCase

import com.example.solitodo.repository.TaskRepository

class DeleteTaskUseCase(private val taskRepository: TaskRepository ) {

    operator fun invoke(id: Int)
    {
        taskRepository.delete(id)
    }
}