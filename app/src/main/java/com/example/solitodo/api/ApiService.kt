package com.example.solitodo.api
import com.example.solitodo.Data.Todo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("todos/2")
    //fun getTaskById(@Path("id") id: Int): Call<Task>
    fun getTaskById(): Call<Todo>

    @GET("todos/1")
    suspend fun getTask(): Todo
}