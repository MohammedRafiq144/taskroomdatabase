package com.example.taskdatasense.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskdatasense.RoomDatabase.UserDetails
import com.example.taskdatasense.RoomDatabase.loginDb.LoginDetails
import com.example.taskdatasense.repo.UserRepository

class MainModel(private val repository: UserRepository) : ViewModel() {
    val userData = MutableLiveData<UserDetails>()
    val loginData = MutableLiveData<LoginDetails>()
    fun getAllUserData(): ArrayList<UserDetails> {
        return repository.getAllUserRepo() as ArrayList
    }

}