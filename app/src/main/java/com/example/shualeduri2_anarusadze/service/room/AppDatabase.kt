package com.example.shualeduri2_anarusadze.service.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shualeduri2_anarusadze.users.model.UserModel


@Database(entities = [UserModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUsersDao(): UsersDao
}