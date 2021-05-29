package com.example.shualeduri2_anarusadze

import android.app.Application
import androidx.room.Room
import com.example.shualeduri2_anarusadze.service.retrofit.RetrofitClient
import com.example.shualeduri2_anarusadze.service.room.AppDatabase

class App : Application() {

    lateinit var db: AppDatabase

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "APP_DATABASE"
        ).allowMainThreadQueries().build()

        RetrofitClient.initClient()
    }

}