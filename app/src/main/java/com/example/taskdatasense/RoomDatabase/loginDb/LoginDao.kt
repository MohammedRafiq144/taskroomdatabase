package com.example.taskdatasense.RoomDatabase.loginDb

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import com.example.taskdatasense.RoomDatabase.AppDatabase
import com.example.taskdatasense.RoomDatabase.UserDao
import com.example.taskdatasense.RoomDatabase.UserDetails
@Dao
interface LoginDao {
    @Query("SELECT * FROM logindatabasetable")
    fun getLoginData(): List<LoginDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoginData(vararg loginDetails: LoginDetails)
}