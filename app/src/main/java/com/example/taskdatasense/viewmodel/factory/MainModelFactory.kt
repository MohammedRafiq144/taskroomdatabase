package com.example.taskdatasense.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskdatasense.repo.LoginRepository
import com.example.taskdatasense.repo.UserRepository
import com.example.taskdatasense.viewmodel.LoginModel
import com.example.taskdatasense.viewmodel.MainModel

class MainModelFactory internal constructor(private val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.canonicalName == MainModel::class.java.canonicalName) {
            return MainModel(UserRepository.getInstance(context = context)) as T
        } else {
            throw Exception("No data available")
        }
    }
}