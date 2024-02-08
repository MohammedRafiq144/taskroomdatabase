package com.example.taskdatasense.repo

import android.app.Application
import android.content.Context
import com.example.taskdatasense.RoomDatabase.AppDatabase
import com.example.taskdatasense.RoomDatabase.UserDetails
import com.example.taskdatasense.RoomDatabase.loginDb.LoginDataBase
import java.lang.reflect.Executable
import java.util.concurrent.Callable
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class UserRepository(context: Context) {
    private val userDao = AppDatabase.createDataBase(context).userDao()

    fun getAllUserRepo(): List<UserDetails> {
        return Executors.newSingleThreadExecutor().submit(Callable {
            userDao.getAllData()
        }).get()
    }

    fun insertUserDetails(userDetails: UserDetails) {
        Thread {
            userDao.getInsertData(userDetails)
        }.start()
    }
    companion object {
        fun getInstance(context: Context) = UserRepository(context)
    }

}