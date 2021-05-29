package com.example.shualeduri2_anarusadze.users.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.shualeduri2_anarusadze.App
import com.example.shualeduri2_anarusadze.R
import com.example.shualeduri2_anarusadze.service.retrofit.RetrofitClient
import com.example.shualeduri2_anarusadze.todo.ui.TodoActivity
import com.example.shualeduri2_anarusadze.users.adapter.UsersAdapter
import com.example.shualeduri2_anarusadze.users.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersActivity : AppCompatActivity() {

    private var userList = mutableListOf<UserModel>()
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var usersRecyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var usersTotalCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        usersRecyclerView = findViewById(R.id.usersRecyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        usersTotalCount = findViewById(R.id.totalUsersTextView)

        swipeRefreshLayout.setOnRefreshListener {
            refresh()
        }

        fetchData()
    }

    private fun fetchData() {
        RetrofitClient.apiService.getUsers().enqueue(object : Callback<List<UserModel>> {
            override fun onResponse(call: Call<List<UserModel>>, response: Response<List<UserModel>>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.forEach { user ->
                        Log.d("UserData", user.toString())
                        userList.add(user)
                        App.instance.db.getUsersDao().insert(user)
                    }
                    getUsersCount()
                    setAdapter()
                }
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Log.d("UserData", t.message.toString())
            }
        })

    }

    @SuppressLint("SetTextI18n")
    private fun getUsersCount(){
        val count = userList.size
        usersTotalCount.text = "Total Users: $count"
    }

    private fun setAdapter() {
        usersAdapter = UsersAdapter(userList) { id: Int, name: String -> navigateToTodoActivity(id, name) }
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecyclerView.adapter = usersAdapter
    }

    private fun navigateToTodoActivity(id: Int, name: String) {
        val intent = Intent(this, TodoActivity::class.java).apply {
            putExtra("USER_ID", id)
            putExtra("USER_NAME", name)
        }
        startActivity(intent)
    }


    private fun refresh() {
        swipeRefreshLayout.isRefreshing = true

        Handler(Looper.getMainLooper()).postDelayed({
            swipeRefreshLayout.isRefreshing = false
            userList = getDataFromRoom()

            usersAdapter.notifyDataSetChanged()
        }, 2000)
    }


    private fun getDataFromRoom() : MutableList<UserModel>{
        App.instance.db.getUsersDao().apply {
            getAllUsers().forEach { user -> Log.d("RoomData", user.toString()) }
            return getAllUsers()
        }
    }
}