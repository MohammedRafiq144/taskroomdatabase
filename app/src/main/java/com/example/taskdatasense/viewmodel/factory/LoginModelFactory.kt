package com.example.taskdatasense.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskdatasense.repo.LoginRepository
import com.example.taskdatasense.viewmodel.LoginModel

class LoginModelFactory internal constructor(private val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.canonicalName == LoginModel::class.java.canonicalName) {
            return LoginModel(LoginRepository.getInstance(context = context)) as T
        } else {
            throw Exception("No data available")
        }
    }
}