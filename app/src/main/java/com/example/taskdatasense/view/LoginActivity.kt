package com.example.taskdatasense.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taskdatasense.R
import com.example.taskdatasense.RoomDatabase.loginDb.LoginDetails
import com.example.taskdatasense.databinding.ActivityLoginBinding
import com.example.taskdatasense.repo.LoginRepository
import com.example.taskdatasense.viewmodel.LoginModel
import com.example.taskdatasense.viewmodel.factory.LoginModelFactory

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            validation(binding.usernameEdt, binding.passwordEdt)
        }

        viewModel = ViewModelProvider(
            this@LoginActivity,
            LoginModelFactory(applicationContext)
        ).get(LoginModel::class.java)

    }

    private fun validation(userName: EditText, password: EditText) {
        if (userName.text.isEmpty() && password.text.isEmpty()) {
            Toast.makeText(this, "Do not empty your fields", Toast.LENGTH_SHORT).show()
        } else {

            viewModel.apply {

                loginData.postValue(
                    LoginDetails(
                        userName = userName.text.toString(),
                        password = password.text.toString()
                    )
                )

                loginData.observe(this@LoginActivity, Observer {
                    val loginRepo = LoginRepository.getInstance(applicationContext)
                    loginRepo.insertUserDetails(loginDetails = it)
                })

            }

            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
        }
    }
}