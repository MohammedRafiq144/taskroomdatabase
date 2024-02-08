package com.example.taskdatasense.RoomDatabase.loginDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskdatasense.RoomDatabase.AppDatabase
import com.example.taskdatasense.RoomDatabase.UserDao
import com.example.taskdatasense.RoomDatabase.UserDetails


@Database(entities = [LoginDetails::class], version = 1, exportSchema = false)
abstract class LoginDataBase:RoomDatabase() {
    abstract fun loginDao(): LoginDao
    companion object {
        fun createDataBase(applicationContext: Context): LoginDataBase {
            return Room.databaseBuilder(
                applicationContext,
                LoginDataBase::class.java,
                "database-name"
            ).build()
        }
    }
}