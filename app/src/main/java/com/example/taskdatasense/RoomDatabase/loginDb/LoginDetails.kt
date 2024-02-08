package com.example.taskdatasense.RoomDatabase.loginDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LoginDatabaseTable")
data class LoginDetails(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    var userName:String,
    var password:String
)