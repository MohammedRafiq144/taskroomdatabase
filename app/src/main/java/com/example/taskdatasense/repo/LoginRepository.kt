package com.example.taskdatasense.repo

import android.content.Context
import com.example.taskdatasense.RoomDatabase.loginDb.LoginDataBase
import com.example.taskdatasense.RoomDatabase.loginDb.LoginDetails
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class LoginRepository(context: Context) {
    private val loginDao = LoginDataBase.createDataBase(context).loginDao()

    fun getUserRepos(): List<LoginDetails> {
        return Executors.newSingleThreadExecutor().submit(Callable {
            loginDao.getLoginData()
        }).get()
    }
    fun insertUserDetails(loginDetails: LoginDetails) {
        Thread {
            loginDao.insertLoginData(loginDetails)
        }.start()
    }
    companion object {
        fun getInstance(context: Context) = LoginRepository(context)
    }
}