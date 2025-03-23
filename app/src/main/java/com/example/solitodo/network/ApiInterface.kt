package com.example.solitodo.network

import com.example.solitodo.dao.TaskDao
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Path

interface ApiInterface {
    @GET("tasks")
    fun getTasks(): Call<List<TaskDao>>

    @POST("tasks")
    fun createTask(@Body task: TaskDao): Call<Unit>

    @POST("tasks/{id}/edit")
    fun updateTask(@Path("id") id: Int, @Body task: TaskDao): Call<Unit>

    @DELETE("tasks/{id}")
    fun deleteTask(@Path("id") id: Int): Call<Boolean>
}