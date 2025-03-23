package com.example.solitodo.repository

import android.util.Log
import com.example.solitodo.dao.TaskDao
import com.example.solitodo.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository (private val apiService: ApiService): Repository {
    private val _tasks: MutableStateFlow<List<TaskDao>> = MutableStateFlow(emptyList());
    val tasks: StateFlow<List<TaskDao>> get() {
        all()
        return _tasks
    }
    override fun all() {
        apiService.apiInterface.getTasks().enqueue(object: Callback<List<TaskDao>>{
            override fun onResponse(
                call: Call<List<TaskDao>>,
                response: Response<List<TaskDao>>
            ) {
                _tasks.value = response.body() ?: emptyList()
            }

            override fun onFailure(
                call: Call<List<TaskDao>?>,
                t: Throwable
            ) {
                println(t.message)
                _tasks.value = emptyList()
            }

        } )
    }

    override fun find(id: Int) {
        TODO("Not yet implemented")
    }

    override fun create(data: TaskDao) {
        apiService.apiInterface.createTask(data).enqueue(object: Callback<Unit>{
            override fun onResponse(
                call: Call<Unit?>,
                response: Response<Unit?>
            ) {
                println("Item Created")
            }

            override fun onFailure(call: Call<Unit?>, t: Throwable) {
                println(t.message)
            }
        })
    }

    override fun update(id: Int, data: TaskDao) {
        apiService.apiInterface.updateTask(id, data).enqueue(object: Callback<Unit>{
            override fun onResponse(
                call: Call<Unit?>,
                response: Response<Unit?>
            ) {
                println("Item Updated")
            }

            override fun onFailure(call: Call<Unit?>, t: Throwable) {
                println(t.message)
            }
        })
    }

    override fun delete(id: Int) {
        apiService.apiInterface.deleteTask(id).enqueue(object: Callback<Boolean>{
            override fun onResponse(
                call: Call<Boolean?>,
                response: Response<Boolean?>
            ) {
                println("Item Deleted Successfully")
            }

            override fun onFailure(call: Call<Boolean?>, t: Throwable) {
                println(t.message)
            }
        })
    }
}