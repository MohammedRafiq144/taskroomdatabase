package com.example.taskdatasense.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskdatasense.RoomDatabase.UserDetails
import com.example.taskdatasense.RoomDatabase.loginDb.LoginDetails
import com.example.taskdatasense.repo.LoginRepository
import com.example.taskdatasense.repo.UserRepository

class LoginModel(private val repository: LoginRepository) : ViewModel() {
    val loginData = MutableLiveData<LoginDetails>()
    fun getAllUserData(): ArrayList<LoginDetails> {
        return repository.getUserRepos() as ArrayList
    }
}