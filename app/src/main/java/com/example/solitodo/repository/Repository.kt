package com.example.solitodo.repository

import com.example.solitodo.dao.TaskDao

interface Repository {
    fun all();
    fun find(id: Int);
    fun create(data: TaskDao);
    fun update(id:Int, data: TaskDao)
    fun delete(id: Int)
}