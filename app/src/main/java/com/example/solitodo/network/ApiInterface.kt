package com.example.solitodo.network

import com.example.solitodo.dao.TaskDao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.DELETE
import retrofit2.http.Path

interface ApiInterface {
    @GET("tasks")
    fun getTasks(): Call<List<TaskDao>>

    @DELETE("tasks/{id}")
    fun deleteTask(@Path("id") id: Int): Call<Boolean>
}