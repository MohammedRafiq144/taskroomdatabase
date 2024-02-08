package com.example.taskdatasense.RoomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// name, age, date of birth, education, and gender.
@Entity(tableName = "TestUserDataTable")
data class UserDetails(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    var name: String,
    var age: String,
    var dateOfBirth: String,
    var education: String,
    var gender: String
)