package com.example.shualeduri2_anarusadze.service.room

import androidx.room.*
import com.example.shualeduri2_anarusadze.users.model.UserModel

@Dao
interface UsersDao {

    @Query("SELECT * FROM USERS")
    fun getAllUsers(): MutableList<UserModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: UserModel)

    @Query("DELETE FROM USERS")
    fun deleteAll()

}