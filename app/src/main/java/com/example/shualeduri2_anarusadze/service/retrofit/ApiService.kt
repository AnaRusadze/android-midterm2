package com.example.shualeduri2_anarusadze.service.retrofit

import com.example.shualeduri2_anarusadze.todo.model.TodoModel
import com.example.shualeduri2_anarusadze.users.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("users")
    fun getUsers(): Call<List<UserModel>>

    @GET("users/{userId}/todos")
    fun getTodos(@Path("userId") userId: Int): Call<List<TodoModel>>

}