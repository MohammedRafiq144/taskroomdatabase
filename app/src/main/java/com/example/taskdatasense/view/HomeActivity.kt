package com.example.taskdatasense.view

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taskdatasense.R
import com.example.taskdatasense.RoomDatabase.UserDetails
import com.example.taskdatasense.databinding.ActivityHomeBinding
import com.example.taskdatasense.repo.UserRepository
import com.example.taskdatasense.viewmodel.MainModel
import com.example.taskdatasense.viewmodel.factory.MainModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private var calender: Calendar = Calendar.getInstance()
    private lateinit var dobValue: String
    private lateinit var viewmodel: MainModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView3.setOnClickListener {
            val i = Intent(this, AddUserActivity::class.java)
            startActivity(i)
            finish()
        }

        binding.addUserBtn.setOnClickListener {
            validation()
        }

        val date: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, day ->
                calender.set(Calendar.YEAR, year)
                calender.set(Calendar.MONTH, month)
                calender.set(Calendar.DAY_OF_MONTH, day)
                updateDateLabel()
            }

        binding.userDobEdt.setOnClickListener {
            DatePickerDialog(
                this,
                date,
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        viewmodel = ViewModelProvider(
            this@HomeActivity,
            MainModelFactory(this)
        ).get(MainModel::class.java)

    }

    private fun validation() {
        if (binding.userNameEdt.text.isEmpty() &&
            binding.userAgeEdt.text.isEmpty() &&
            binding.userDobEdt.text.isEmpty() &&
            binding.userEducationEdt.text.isEmpty()
        ) {
            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show()
        } else if (binding.userAgeEdt.text.length < 2 || binding.userAgeEdt.text.toString() == "0") {
            Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show()
        } else if (binding.userDobEdt.text.toString() == "0") {
            Toast.makeText(this, "Please enter your dob", Toast.LENGTH_SHORT).show()
        } else if (!binding.maleRadioBtn.isChecked && !binding.femaleRadioBtn.isChecked) {
            Toast.makeText(this, "Please enter your gender", Toast.LENGTH_SHORT).show()
        } else {
            setAllValuesInDB()
        }
    }

    private fun setAllValuesInDB() {

        val getId: Int = binding.genderRg.checkedRadioButtonId
        val button: RadioButton = findViewById(getId)
        Toast.makeText(this, button.text, Toast.LENGTH_SHORT).show()

        viewmodel.apply {
            userData.postValue(
                UserDetails(
                    name = binding.userNameEdt.text.toString(),
                    age = binding.userAgeEdt.text.toString(),
                    dateOfBirth = binding.userDobEdt.text.toString(),
                    education = binding.userEducationEdt.text.toString(),
                    gender = button.text.toString()
                )
            )
            userData.observe(this@HomeActivity, Observer {
                val userRepo = UserRepository.getInstance(this@HomeActivity)
                userRepo.insertUserDetails(userDetails = it)
            })

        }

        val i = Intent(this, AddUserActivity::class.java)
        startActivity(i)
        finish()
        Toast.makeText(this, "User successfully added", Toast.LENGTH_SHORT).show()


    }

    private fun updateDateLabel() {
        val myFormat: String = "MM/dd/yy"
        val dateFormat: SimpleDateFormat = SimpleDateFormat(myFormat, Locale.US)
        binding.userDobEdt.setText(dateFormat.format(calender.time))
        dobValue = binding.userDobEdt.text.toString()
    }
}