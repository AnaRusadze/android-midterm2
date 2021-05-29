package com.example.shualeduri2_anarusadze.users.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USERS")
data class UserModel(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "USER_ID")
    var id: Int,

    @ColumnInfo(name = "USER_NAME")
    val name: String,

    @ColumnInfo(name = "USER_USERNAME")
    val username: String,

    @ColumnInfo(name = "USER_EMAIL")
    val email: String,

    @ColumnInfo(name = "USER_PHONE")
    val phone: String,

    @ColumnInfo(name = "USER_WEBSITE")
    val website: String
)


