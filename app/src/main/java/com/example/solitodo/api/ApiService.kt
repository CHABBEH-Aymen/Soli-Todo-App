package com.example.solitodo.api

import com.example.solitodo.Data.Todo
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET
    fun tasks(): Call<Todo>
}