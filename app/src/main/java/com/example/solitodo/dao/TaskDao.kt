package com.example.solitodo.dao

data class TaskDao (
    val id: Int? = null,
    val title: String,
    val description: String = "empty"
)