package com.example.shualeduri2_anarusadze.todo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shualeduri2_anarusadze.R
import com.example.shualeduri2_anarusadze.service.retrofit.RetrofitClient
import com.example.shualeduri2_anarusadze.todo.adapter.TodoAdapter
import com.example.shualeduri2_anarusadze.todo.model.TodoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoActivity : AppCompatActivity() {

    private val todoList = mutableListOf<TodoModel>()
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var todoRecyclerView: RecyclerView
    private lateinit var userName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        todoRecyclerView = findViewById(R.id.todoRecyclerView)
        userName = findViewById(R.id.usernameTextView)

        fetchData()
        setUserName()
    }

    private fun fetchData() {
        val userId = intent.getIntExtra("USER_ID", -1)
        RetrofitClient.apiService.getTodos(userId).enqueue(object : Callback<List<TodoModel>> {
            override fun onResponse(
                call: Call<List<TodoModel>>,
                response: Response<List<TodoModel>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.forEach { user ->
                        Log.d("TodoData", user.toString())
                        todoList.add(user)
                    }
                    setAdapter()
                }
            }
            override fun onFailure(call: Call<List<TodoModel>>, t: Throwable) {
                Log.d("TodoData", t.message.toString())
            }
        })
    }

    private fun setAdapter(){
        todoAdapter = TodoAdapter(todoList)
        todoRecyclerView.layoutManager = LinearLayoutManager(this)
        todoRecyclerView.adapter = todoAdapter
    }

    private fun setUserName(){
        val name = intent.getStringExtra("USER_NAME")
        userName.text = name
    }

}
